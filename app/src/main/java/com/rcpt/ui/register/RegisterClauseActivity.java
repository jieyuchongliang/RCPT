package com.rcpt.ui.register;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.RegisterClauseBean;
import com.rcpt.databinding.ActivityRegisterClauseBinding;
import com.rcpt.mvp.contract.RegisterClauseContract;
import com.rcpt.mvp.presenter.RegisterClausePresenter;

public class RegisterClauseActivity extends BaseActivity<ActivityRegisterClauseBinding, RegisterClauseContract.View, RegisterClausePresenter>
        implements RegisterClauseContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("注册条款");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {

        mDataBinding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                updateLoadingProgress(newProgress);
            }
        });

        mDataBinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoadingProgress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mDataBinding.loadingProgress.setProgress(100);
                hintLoadingProgress();
            }
        });
        mPresenter.loadPageData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_clause;
    }

    @Override
    public void bindWebData(RegisterClauseBean bean) {
        mDataBinding.webView.loadDataWithBaseURL("", bean.getContent(), "text/html", "utf-8", "");
    }

    @Override
    public void showLoadingProgress() {
        if (mDataBinding.loadingProgress.getVisibility() == View.GONE)
            mDataBinding.loadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintLoadingProgress() {
        if (mDataBinding.loadingProgress.getVisibility() == View.VISIBLE)
            mDataBinding.loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void updateLoadingProgress(int progress) {
        mDataBinding.loadingProgress.setProgress(progress);
    }

    @Override
    protected RegisterClausePresenter createPresenter() {
        return new RegisterClausePresenter();
    }
}
