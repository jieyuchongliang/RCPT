package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.MessageContract;
import com.rcpt.ui.me.message.InterviewMessageActivity;
import com.rcpt.ui.me.message.SystemMessageActivity;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {

    @Override
    public void attach(MessageContract.View view) {
        super.attach(view);
        getView().onBindBadgeView(getView().getInterviewMessageLayout(), 10);
        getView().onBindBadgeView(getView().getSystemMessageLayout(), 3);
    }

    @Override
    public void onClickGoInterviewMessage() {
        getView().actionStartActivity(InterviewMessageActivity.class);
    }

    @Override
    public void onClickGoSystemMessage() {
        getView().actionStartActivity(SystemMessageActivity.class);
    }
}
