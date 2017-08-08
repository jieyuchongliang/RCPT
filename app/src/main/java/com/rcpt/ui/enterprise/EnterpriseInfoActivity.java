package com.rcpt.ui.enterprise;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.EnterpriseInfoBean;
import com.rcpt.databinding.ActivityEnterpriseInfoBinding;
import com.rcpt.databinding.ItemLayoutEnterpriseInfoJobListBinding;
import com.rcpt.mvp.contract.EnterpriseInfoContract;
import com.rcpt.mvp.presenter.EnterpriseInfoPresenter;

import java.util.List;

/**
 * 企业详情
 */
public class EnterpriseInfoActivity extends BaseActivity<ActivityEnterpriseInfoBinding, EnterpriseInfoContract.View, EnterpriseInfoPresenter>
        implements EnterpriseInfoContract.View {

    private static final String ID_TAG = "id";
    private SimpleBindingAdapter<EnterpriseInfoBean.JobListBean> mAdapter;

    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, EnterpriseInfoActivity.class);
        intent.putExtra(ID_TAG, id);
        context.startActivity(intent);
    }

    @Override
    protected void setupTitle() {
        setTitleText("企业详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadInfo();
        initRecyclerView();
    }

    private void initRecyclerView() {

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<EnterpriseInfoBean.JobListBean>(R.layout.item_layout_enterprise_info_job_list, BR.jobListBean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, int position, EnterpriseInfoBean.JobListBean jobListBean) {
                super.bindingViews(holder, position, jobListBean);
                ItemLayoutEnterpriseInfoJobListBinding binding = (ItemLayoutEnterpriseInfoJobListBinding) holder.getBinding();
                binding.btnFind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.onClickGoFind(holder.getLayoutPosition());
                    }
                });
            }
        };

        mDataBinding.recyclerView.setAdapter(mAdapter);

    }
    @Override
    public void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images) {
        if (images == null) {
            mDataBinding.convenientBanner.setVisibility(View.GONE);
        }else if (images.size() > 1) {
            mDataBinding.convenientBanner
                    .setPages(holderCreator, images)
                    .setPageIndicator(new int[]{R.drawable.ic_banner_page_indicator, R.drawable.ic_banner_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .startTurning(3000);
        }else {
            mDataBinding.convenientBanner
                    .setPages(holderCreator, images)
                    .setManualPageable(false);
        }

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterprise_info;
    }

    @Override
    protected EnterpriseInfoPresenter createPresenter() {
        return new EnterpriseInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void bindData(EnterpriseInfoBean bean) {
        mDataBinding.setEnterpriseInfo(bean);
        mAdapter.setupData(bean.getJobList());
    }

    @Override
    public String getEnterpriseId() {
        return getIntent().getStringExtra(ID_TAG);
    }

}
