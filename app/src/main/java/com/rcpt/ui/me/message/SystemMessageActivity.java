package com.rcpt.ui.me.message;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.SystemMessageAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.databinding.ActivitySystemMessageBinding;
import com.rcpt.mvp.contract.SystemMessageContract;
import com.rcpt.mvp.presenter.SystemMessagePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.system.PushManageActivity;
import com.rcpt.utils.NewMessageNotification;

import java.util.List;

@SuppressLint("WrongConstant")
public class SystemMessageActivity extends BaseActivity<ActivitySystemMessageBinding, SystemMessageContract.View, SystemMessagePresenter>
        implements SystemMessageContract.View, SpringView.OnFreshListener {

    private static final int PUSH_MANAGE_REQUEST_CODE = 0x0000101e;

    private SystemMessageAdapter mAdapter;
    private boolean isEnd;
    private int mClickItemPosition;

    @Override
    protected void setupTitle() {
        setTitleText("消息列表");
        openTitleLeftView(true);
        //判断只有在为管理员的身份下，才有发布消息的权限
        if (Constant.UserType.MANAGE.getValue().equals(LoginHelper.getInstance().getUserBean().getUserType())) {
            CheckBox rightView = getTitleRightView();
            rightView.setVisibility(View.VISIBLE);
            rightView.setButtonDrawable(R.drawable.ic_vectror_add2);
            rightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, PushManageActivity.class);
                    startActivityForResult(intent, PUSH_MANAGE_REQUEST_CODE); /**/
                }
            });
        }
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    protected SystemMessagePresenter createPresenter() {
        return new SystemMessagePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener());
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SystemMessageAdapter(R.layout.item_layout_system_message);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
    }


    @Override
    public void bindListData(List<SystemMessageBean.MessageListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }


    /**
     * 获取点击条目的位置
     *
     * @return
     */
    @Override
    public int getClickItemPosition() {
        return mClickItemPosition;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PUSH_MANAGE_REQUEST_CODE:
                    onRefresh();
                    break;
            }
        }
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener() {
            super(mDataBinding.recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mClickItemPosition = vh.getLayoutPosition();
            mPresenter.onItemClick();
        }
    }
}
