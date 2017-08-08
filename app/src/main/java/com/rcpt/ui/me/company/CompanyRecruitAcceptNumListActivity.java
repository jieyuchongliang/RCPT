package com.rcpt.ui.me.company;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CompanyRecruitAcceptNumListBean;
import com.rcpt.databinding.ActivityCompanyRecruitAcceptNumListBinding;
import com.rcpt.databinding.ItemLayoutCompanyRecruitAcceptNumListBinding;
import com.rcpt.databinding.LayoutDialogSendInterviewNoticeBinding;
import com.rcpt.mvp.contract.CompanyRecruitAcceptNumListContract;
import com.rcpt.mvp.presenter.CompanyRecruitAcceptNumListPresenter;
import com.rcpt.ui.recruit.RecruitCVPreviewInfoActivity;
import com.rcpt.utils.DialogUtils;
import com.rcpt.widget.DateTimePickerDialog;

import java.util.List;

public class CompanyRecruitAcceptNumListActivity extends BaseActivity<ActivityCompanyRecruitAcceptNumListBinding, CompanyRecruitAcceptNumListContract.View, CompanyRecruitAcceptNumListPresenter>
        implements CompanyRecruitAcceptNumListContract.View, SpringView.OnFreshListener, DateTimePickerDialog.OnDateTimeSetListener {


    public static void actionStart(Context context, String jobName, String recruitmentInfoId) {
        Intent intent = new Intent(context, CompanyRecruitAcceptNumListActivity.class);
        intent.putExtra("job_name", jobName);
        intent.putExtra("recruitment_id", recruitmentInfoId);
        context.startActivity(intent);
    }

    private String mJobName;
    private SimpleBindingAdapter<CompanyRecruitAcceptNumListBean.JobPeopleListBean> mAdapter;
    private boolean isEnd;
    private LayoutDialogSendInterviewNoticeBinding mSendNoticeDialogBinding;
    private Dialog mSendNoticeDialog;
    private long mDateTimeSelectData;
    private int mClickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mJobName = getIntent().getStringExtra("job_name");

    }

    @Override
    public String getRecruitmentInfoId() {
        return getIntent().getStringExtra("recruitment_id");
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText(mJobName);
        openTitleLeftView(true);
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<CompanyRecruitAcceptNumListBean.JobPeopleListBean>(R.layout.item_layout_company_recruit_accept_num_list, BR.bean) {
            @Override
            protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, final int position, final CompanyRecruitAcceptNumListBean.JobPeopleListBean bean) {
                super.bindingViews(holder, position, bean);
                ItemLayoutCompanyRecruitAcceptNumListBinding binding = (ItemLayoutCompanyRecruitAcceptNumListBinding) holder.getBinding();
                int status = bean.getStatus();
                int is_adopt = bean.getIS_ADOPT();
                if (is_adopt== -1) {
                    if (status < 0) {
                        binding.btnNotice.setEnabled(true);
                        binding.btnNotice.setBackgroundResource(R.drawable.bg_orange_btn);
                        binding.btnNotice.setText("通知");
                        binding.btnNotice.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mClickPosition = position;
                                mPresenter.onClickSendNotice();
                            }
                        });
                    } else {
                        binding.btnNotice.setEnabled(false);
                        if (status == 0) {
                            binding.btnNotice.setBackgroundResource(R.drawable.bg_gray_btn);
                            binding.btnNotice.setText("已通知");
                        } else if (status == 1) {
                            binding.btnNotice.setBackgroundResource(R.drawable.bg_gray_btn);
                            binding.btnNotice.setText("同意面试");
                        } else if (status == 2) {
                            binding.btnNotice.setBackgroundResource(R.drawable.bg_gray_btn);
                            binding.btnNotice.setText("已拒绝");
                        }

                    }
                }else {
                    if (is_adopt == 0 || is_adopt == 2) {
                        binding.btnNotice.setBackgroundResource(R.drawable.bg_gray_btn);
                        binding.btnNotice.setText("已通过");
                    }
                    if (is_adopt == 1){
                        binding.btnNotice.setBackgroundResource(R.drawable.bg_gray_btn);
                        binding.btnNotice.setText("未通过");
                    }
                }

                //应聘者名称的点击事件
                binding.tvAcceptName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickPosition = position;
                        if (bean.getBeenViewed().equals("未阅览")){
                            bean.setBeenViewed("已阅览");
                            mPresenter.updateReadingStatus(bean.getCvId(), bean.getUserId());
                            notifyItemChanged(mClickPosition);
                        }
                        else
                            RecruitCVPreviewInfoActivity.actionStart(mContext, bean.getCvId(), bean.getUserId());
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    /**
     * 获取点击的条目
     *
     * @return
     */
    @Override
    public int getClickPosition() {
        return mClickPosition;
    }

    @Override
    public void bindListData(List<CompanyRecruitAcceptNumListBean.JobPeopleListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    /**
     * 更新列表的条目
     *
     * @param position
     */
    @Override
    public void updateListItemForPosition(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
        initDialog();
    }

    private void initDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.layout_dialog_send_interview_notice, null);
        mSendNoticeDialogBinding = DataBindingUtil.bind(dialogView);
        mSendNoticeDialogBinding.setPresenter(mPresenter);
        mSendNoticeDialog = DialogUtils.builderEmptyDialog(dialogView);
        mSendNoticeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mSendNoticeDialogBinding.tvStartTime.setText("");
                mSendNoticeDialogBinding.tvEndTime.setText("");
                mSendNoticeDialogBinding.editDescribeContent.setText("");
            }
        });
    }

    /**
     * 获取开始的时间
     *
     * @return
     */
    @Override
    public String getSelectStartTime() {
        return mSendNoticeDialogBinding.tvStartTime.getText().toString();
    }

    /**
     * 获取结束的时间
     *
     * @return
     */
    @Override
    public String getSelectEndTime() {
        return mSendNoticeDialogBinding.tvEndTime.getText().toString();
    }

    /**
     * 获取内容描述
     */
    @Override
    public String getDescribeContentText() {
        return mSendNoticeDialogBinding.editDescribeContent.getText().toString();
    }

    /**
     * 隐藏发送面试通知的Dialog
     */
    @Override
    public void hideSendNoticeDialog() {
        mSendNoticeDialog.dismiss();
    }

    /**
     * 显示发送面试通知的Dialog
     */
    @Override
    public void showSendNoticeDialog() {
        mSendNoticeDialog.show();
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


    /**
     * 显示日期时间选择器
     */
    @Override
    public void showDateTimeSelectDialog(long dateTime) {
        if (dateTime == -1) {
            dateTime = System.currentTimeMillis();
        }
        DateTimePickerDialog dialog = new DateTimePickerDialog(this, dateTime);
        dialog.setOnDateTimeSetListener(this);
        dialog.show();
    }

    /**
     * 获取时间选择器选择后的数据
     *
     * @return
     */
    @Override
    public long getDateTimeSelectData() {
        return mDateTimeSelectData;
    }


    @Override
    public void OnDateTimeSet(AlertDialog dialog, long date) {
        mDateTimeSelectData = date;
        mPresenter.onDialogClickSetting();
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
    protected int getLayoutId() {
        return R.layout.activity_company_recruit_accept_num_list;
    }

    @Override
    protected CompanyRecruitAcceptNumListPresenter createPresenter() {
        return new CompanyRecruitAcceptNumListPresenter();
    }
}
