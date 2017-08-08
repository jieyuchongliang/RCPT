package com.rcpt.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityMainBinding;
import com.rcpt.mvp.contract.MainContract;
import com.rcpt.mvp.presenter.MainPresenter;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.ui.main.fragment.EnterpriseFragment;
import com.rcpt.ui.main.fragment.HomeFragment;
import com.rcpt.ui.main.fragment.MeFragment;
import com.rcpt.ui.main.fragment.RecruitFragment;
import com.rcpt.ui.system.SystemMainActivity;
import com.rcpt.utils.AppBottomLayoutManager;
import com.rcpt.widget.TabItem;

public class MainActivity extends BaseActivity<ActivityMainBinding,
        MainContract.View, MainPresenter> implements MainContract.View {

    private static final int LOGIN_REQUEST_CODE = 0x0000321;

    private AppBottomLayoutManager mBottomLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        isUseDefaultTitle = false;
    }

    @Override
    protected void setupTitle() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            changeCurrentItem(Constant.AppModel.HOME.getValue());
        }
    }

    @Override
    protected void initViews() {

        ViewCompat.setElevation(mDataBinding.llActivityMainBottom, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));

        mBottomLayoutManager = new AppBottomLayoutManager(mContext, mDataBinding.llActivityMainBottom);

        mBottomLayoutManager.setContainerViewId(R.id.fl_activity_main_content);

        mBottomLayoutManager.addFragment(Constant.AppModel.HOME.getValue(), HomeFragment.newInstance());
        mBottomLayoutManager.addFragment(Constant.AppModel.ENTERPRISE.getValue(), EnterpriseFragment.newInstance());
        mBottomLayoutManager.addFragment(Constant.AppModel.RECRUIT.getValue(), RecruitFragment.newInstance());
        mBottomLayoutManager.addFragment(Constant.AppModel.ME.getValue(), MeFragment.newInstance());

        mBottomLayoutManager.setTabItem(Constant.AppModel.HOME.getValue(), R.drawable.ic_vector_main_home, R.drawable.ic_vector_main_home_unselected, "首页");
        mBottomLayoutManager.setTabItem(Constant.AppModel.ENTERPRISE.getValue(), R.drawable.ic_vector_main_enterprise, R.drawable.ic_vector_main_enterprise_unselected, "企业");
        mBottomLayoutManager.setTabItem(Constant.AppModel.RECRUIT.getValue(), R.drawable.ic_vector_main_recruit, R.drawable.ic_vector_main_recruit_unselected, "招聘");
        mBottomLayoutManager.setTabItem(Constant.AppModel.ME.getValue(), R.drawable.ic_vector_main_me, R.drawable.ic_vector_main_me_unselected, "我的");

        mBottomLayoutManager.setTabImageSize(20);

        mBottomLayoutManager.setOnItemClickChangeListener(new AppBottomLayoutManager.OnItemClickChangeListener() {
            @Override
            public boolean onItemClick(ViewGroup bottomLayout, View item, int position) {
                if (position == Constant.AppModel.ME.getValue()) {
                    if (LoginHelper.getInstance().isOnline())
                        return false;
                    else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivityForResult(intent, LOGIN_REQUEST_CODE);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onItemClickChanger(ViewGroup bottomLayout, View item, int position) {
                setTitleText(item instanceof TabItem ? ((TabItem) item).getTitleText() : "");
            }

            @Override
            public void onItemReselected(ViewGroup bottomLayout, View item, int position) {

            }
        });
        changeCurrentItem(Constant.AppModel.HOME.getValue());
    }

    @Override
    public void changeCurrentItem(int position) {
        if (position >= 0 && position < mBottomLayoutManager.getFragmentCount() && position != mBottomLayoutManager.getCurrentPosition()) {
            mBottomLayoutManager.setCurrentItem(position);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    //退出时的时间
    private long mExitTime;

    /**
     * 对返回键进行监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOGIN_REQUEST_CODE:
                    //当为管理员用户成功登录后，跳转到管理页面，并关闭当前页面
                    actionStartActivity(SystemMainActivity.class);
                    closeActivity();
                    break;
            }
        }
    }
}
