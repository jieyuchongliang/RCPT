package com.rcpt.ui.me.message;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.InterviewMessageListBean;
import com.rcpt.databinding.ActivityInterviewMessageBinding;
import com.rcpt.databinding.ItemLayoutInterviewMessageBinding;
import com.rcpt.databinding.LayoutDialogInterviewMessageInfoBinding;
import com.rcpt.mvp.contract.InterviewMessageContract;
import com.rcpt.mvp.presenter.InterviewMessagePresenter;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * 面试邀请通知的界面
 */
public class InterviewMessageActivity extends BaseActivity<ActivityInterviewMessageBinding, InterviewMessageContract.View, InterviewMessagePresenter>
        implements InterviewMessageContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<InterviewMessageListBean.GetInterviewBean> mAdapter;
    private boolean isEnd;
    private int mClickPosition;

    private LayoutDialogInterviewMessageInfoBinding mDialogBinding;
    private Dialog mInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<InterviewMessageListBean.GetInterviewBean>(R.layout.item_layout_interview_message, BR.bean) {
            @Override
            protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, int position, InterviewMessageListBean.GetInterviewBean bean) {
                super.bindingViews(holder, position, bean);
                ItemLayoutInterviewMessageBinding binding = (ItemLayoutInterviewMessageBinding) holder.getBinding();
                final int fPosition = holder.getAdapterPosition();
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickPosition = fPosition;
                        mPresenter.onItemClick();
                    }
                });
                int status = bean.getStatus();
                String doWithName = "";
                if (status == 0) {
                    doWithName = "是否同意";
                    binding.btnDoWith.setBackgroundResource(R.drawable.bg_orange_btn);
                } else if (status == 1) {
                    doWithName = "已同意";
                    binding.btnDoWith.setBackgroundResource(R.drawable.bg_green_btn);
                } else if (status == 2) {
                    doWithName = "已拒绝";
                    binding.btnDoWith.setBackgroundResource(R.drawable.bg_red_btn);
                }
                binding.btnDoWith.setText(doWithName);
                binding.btnDoWith.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickPosition = fPosition;
                        mPresenter.onClickDisposeInvitation();
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void bindListData(List<InterviewMessageListBean.GetInterviewBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected void setupTitle() {
        setTitleText("面试通知");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        initDialog();
        mPresenter.loadListData();
    }

    private void initDialog() {
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_interview_message_info, null);
        mDialogBinding = DataBindingUtil.bind(dialogView);
        mDialogBinding.setPresenter(mPresenter);
        mInfoDialog = DialogUtils.builderEmptyDialog(dialogView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_interview_message;
    }

    @Override
    protected InterviewMessagePresenter createPresenter() {
        return new InterviewMessagePresenter();
    }

    /**
     * 下拉刷新，回调接口
     */
    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    /**
     * 上拉加载，回调接口
     */
    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 获取拒绝的理由
     *
     * @return
     */
    @Override
    public String getDisagreeContent() {
        return mDialogBinding.editDisagreeContent.getText().toString();
    }

    /**
     * 显示详情Dialog的数据
     *
     * @param bean
     */
    @Override
    public void showInfoDialog(InterviewMessageListBean.GetInterviewBean bean) {
        mDialogBinding.setBean(bean);
        mDialogBinding.btnConfirm.setVisibility(View.GONE);
        mInfoDialog.show();
    }

    /**
     * 隐藏设置面试通知状态的按钮布局
     */
    @Override
    public void hintDialogSettingLayout() {
        mDialogBinding.llSettingParent.setVisibility(View.GONE);
    }

    /**
     * 显示Dialog中的确认按钮
     */
    @Override
    public void showDialogConfirm() {
        mDialogBinding.btnConfirm.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭详情的Dialog
     */
    @Override
    public void infoDialogClose() {
        mInfoDialog.dismiss();
    }

    /**
     * 显示输入拒绝理由的页面
     */
    @Override
    public void showInputDisagreeReasonLayout() {
        mDialogBinding.editDisagreeContent.setText("");
        mDialogBinding.llDisagreeParent.setVisibility(View.VISIBLE);
    }

    /**
     * 获取点击条目的位置
     *
     * @return
     */
    @Override
    public int getClickPosition() {
        return mClickPosition;
    }

    /**
     * 更新列表中显示的某一条数据
     *
     * @param position
     */
    @Override
    public void updateListItem(int position) {
        mAdapter.notifyItemChanged(position);
    }


}
