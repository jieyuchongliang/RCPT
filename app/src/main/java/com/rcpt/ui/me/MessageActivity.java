package com.rcpt.ui.me;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityMessageBinding;
import com.rcpt.mvp.contract.MessageContract;
import com.rcpt.mvp.presenter.MessagePresenter;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * 我的消息界面
 */
public class MessageActivity extends BaseActivity<ActivityMessageBinding, MessageContract.View, MessagePresenter>
        implements MessageContract.View {

    @Override
    protected void setupTitle() {
        setTitleText("我的消息");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View getSystemMessageLayout() {
        return mDataBinding.rlSystem;
    }

    @Override
    public View getInterviewMessageLayout() {
        return mDataBinding.rlInterview;
    }

    @Override
    public void onBindBadgeView(android.view.View targetView, int num) {
        new QBadgeView(mContext).bindTarget(targetView)
                .setBadgeNumber(num)
                .setBadgeTextColor(Color.WHITE)
                .setBadgePadding(6, true)
                .setBadgeTextSize(12, true)
                .setShowShadow(false)
                .setBadgeBackgroundColor(ContextCompat.getColor(mContext, android.R.color.holo_red_light))
                .setBadgeGravity(Gravity.BOTTOM | Gravity.RIGHT | Gravity.END)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                    }
                });
    }
}
