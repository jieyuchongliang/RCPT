package com.rcpt.ui.system;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.User;
import com.rcpt.databinding.ActivityUserManageBinding;
import com.rcpt.mvp.contract.UserManageContract;
import com.rcpt.mvp.presenter.UserManagePresenter;
import com.rcpt.ui.system.fragment.UserManageFragment;

import java.util.List;

public class UserManageActivity extends BaseActivity<ActivityUserManageBinding, UserManageContract.View, UserManagePresenter>
        implements UserManageContract.View {

    public static final int USER_FILTER_REQUEST_CODE = 0x00001342;

    private FragmentAdapter mAdapter;
    private RadioGroup mTitleGroup;
    private int mCurrentFragmentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTitleTabCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener) {
        mTitleGroup.setOnCheckedChangeListener(listener);
    }

    @Override
    public void addPagerChangeListener(ViewPager.OnPageChangeListener listener) {
        mDataBinding.viewPager.addOnPageChangeListener(listener);
    }

    @Override
    public void initViewPager() {
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void setupViewPageWithFragment(List<Fragment> fragmentList, List<String> titleList) {
        User userBean = LoginHelper.getInstance().getUserBean();
        if (userBean.isPersonalRole() && userBean.isGroupRole()) {
            mAdapter.addFragmentList(fragmentList);
            for (int i = 0; i < mTitleGroup.getChildCount(); i++) {
                android.view.View childView = mTitleGroup.getChildAt(i);
                if (childView instanceof RadioButton) {
                    ((RadioButton) childView).setText(titleList.get(i));
                }
            }
            return;
        } else if (userBean.isGroupRole()) {
            mCurrentFragmentPosition = 0;
        } else if (userBean.isPersonalRole()) {
            mCurrentFragmentPosition = 1;
        }
        mAdapter.addFragment(fragmentList.get(mCurrentFragmentPosition));
        setTitleText(titleList.get(mCurrentFragmentPosition));

    }

    /**
     * 当RadioButton点击改变时的回调
     *
     * @param currentItem
     */
    @Override
    public void changeCurrentItem(int currentItem) {
        mCurrentFragmentPosition = currentItem;
        mDataBinding.viewPager.setCurrentItem(currentItem);
    }

    /**
     * 当ViewPager的切换事的回调方法
     *
     * @param position
     */
    @Override
    public void setTitleTabSelect(int position) {
        mCurrentFragmentPosition = position;
        ((RadioButton) mTitleGroup.getChildAt(position)).setChecked(true);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        User userBean = LoginHelper.getInstance().getUserBean();
        if (userBean.isGroupRole() && userBean.isPersonalRole()) {
            mTitleGroup = (RadioGroup) setTitleCenterViewRes(R.layout.layout_tab_white_blue_select, false);
            mPresenter.setupTitle();
        }
        openTitleLeftView(true);
        getTitleRightView().setButtonDrawable(R.drawable.ic_vector_search);
        getTitleRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isGroup = mCurrentFragmentPosition == 0;
                Fragment item = mAdapter.getItem(mCurrentFragmentPosition);
                UserManageFilterActivity.actionStart(item, isGroup, ((UserManageFragment) item).getFilterBean(), USER_FILTER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initViews() {
        mPresenter.initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_manage;
    }

    @Override
    protected UserManagePresenter createPresenter() {
        return new UserManagePresenter();
    }
}
