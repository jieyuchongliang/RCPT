package com.rcpt.ui.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;

import com.lzp.statusbar.utils.ChangeStatusBarStatus;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.User;
import com.rcpt.databinding.ActivityLoginBinding;
import com.rcpt.mvp.contract.LoginContract;
import com.rcpt.mvp.presenter.LoginPresenter;
import com.rcpt.utils.InputMethodUtils;
import com.rcpt.utils.UserUtils;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginContract.View, LoginPresenter> implements LoginContract.View {


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
        //AndroidBug5497Workaround.assistActivity(this);
        //因为这个页面中不需要封装的标题，所以在这里进行状态栏的处理
        ChangeStatusBarStatus.setStatusBarDarkMode(this, true, Color.WHITE);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
        setKeyboardStateListener();
    }

    /**
     * 添加软键盘状态监听器321+4
     */
    protected void setKeyboardStateListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (InputMethodUtils.isKeyboardShown(rootView)) {
                            mDataBinding.slParent.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mDataBinding.slParent.scrollBy(0, mDataBinding.slParent.getHeight());
                                }
                            }, 100);
                            //Log.d("软键盘", "开启");
                        } else {
                            // Log.d("软键盘", "关闭");
                        }
                    }
                }, 100);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public String getInputUserName() {
        return mDataBinding.inputUserName.getInputText().toString().trim();
    }

    @Override
    public String getInputPassword() {
        return mDataBinding.inputPassword.getInputText().toString().trim();
    }

    @Override
    public void saveUser(User user) {
        UserUtils.saveUser(mContext, user);
    }

    @Override
    public void closeMain() {
        setResult(RESULT_OK);
        finish();
    }
}
