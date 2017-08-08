package com.rcpt.ui.me.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.databinding.ActivityMessageInfoBinding;
import com.rcpt.mvp.contract.MessageInfoContract;
import com.rcpt.mvp.presenter.MessageInfoPresenter;

public class MessageInfoActivity extends BaseActivity<ActivityMessageInfoBinding, MessageInfoContract.View, MessageInfoPresenter>
        implements MessageInfoContract.View {

    public static final String INFO_BEAN = "bean";

    public static void actionStart(Context context, SystemMessageBean.MessageListBean bean) {
        Intent intent = new Intent();
        intent.setClass(context, MessageInfoActivity.class);
        intent.putExtra(INFO_BEAN, bean);
        context.startActivity(intent);
    }

    private SystemMessageBean.MessageListBean mMessageBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mMessageBean = getIntent().getParcelableExtra(INFO_BEAN);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("消息详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setMessageBean(mMessageBean);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_info;
    }

    @Override
    protected MessageInfoPresenter createPresenter() {
        return new MessageInfoPresenter();
    }
}
