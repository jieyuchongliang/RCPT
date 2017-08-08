package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.bean.User;
import com.rcpt.mvp.contract.SystemMessageContract;
import com.rcpt.mvp.module.SystemMessageModule;
import com.rcpt.ui.me.message.MessageInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class SystemMessagePresenter extends BasePresenter<SystemMessageContract.View> implements SystemMessageContract.Presenter {

    private SystemMessageModule mModule;

    @Override
    public void attach(SystemMessageContract.View view) {
        super.attach(view);
        mModule = new SystemMessageModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        User userBean = LoginHelper.getInstance().getUserBean();
        mModule.requestMessageList(getView().getContext(), userBean.getUserType(), page, new OnDataGetCallback<List<SystemMessageBean.MessageListBean>>() {
            @Override
            public void onSuccessResult(List<SystemMessageBean.MessageListBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });

    }

    /**
     * 条目的点击事件
     */
    @Override
    public void onItemClick() {
        int position = getView().getClickItemPosition();
        SystemMessageBean.MessageListBean messageListBean = mModule.getListData().get(position);
        MessageInfoActivity.actionStart(getView().getContext(), messageListBean);
    }
}
