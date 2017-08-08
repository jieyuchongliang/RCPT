package com.rcpt.ui.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityAboutMeBinding;
import com.rcpt.mvp.contract.AboutMeContract;
import com.rcpt.mvp.presenter.AboutMePresenter;

public class AboutMeActivity extends BaseActivity<ActivityAboutMeBinding, AboutMeContract.View, AboutMePresenter>
        implements AboutMeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTitleText("关于我们");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected AboutMePresenter createPresenter() {
        return new AboutMePresenter();
    }
}
