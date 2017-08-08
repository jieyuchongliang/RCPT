package com.rcpt.ui.recruit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.AcademyInfoBean;
import com.rcpt.databinding.ActivitySchoolInfoBinding;
import com.rcpt.mvp.contract.SchoolInfoContract;
import com.rcpt.mvp.presenter.SchoolInfoPresenter;

import java.util.List;

/**
 * 校园详情
 */
public class SchoolInfoActivity extends BaseActivity<ActivitySchoolInfoBinding, SchoolInfoContract.View, SchoolInfoPresenter>
        implements SchoolInfoContract.View {

    private SimpleBindingAdapter mAdapter;

    public static void actionStart(Context context, String academyId) {
        Intent intent = new Intent(context, SchoolInfoActivity.class);
        intent.putExtra("academy_id", academyId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
    }

    private void initRecycleView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(mDataBinding.recyclerView);

        mAdapter = new SimpleBindingAdapter(R.layout.item_layout_school_education, BR.bean);

        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTitleText("院校详情");
    }

    @Override
    protected void initViews() {
        initRecycleView();
        mPresenter.loadInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_info;
    }

    @Override
    protected SchoolInfoPresenter createPresenter() {
        return new SchoolInfoPresenter();
    }

    @Override
    public void setupPageData(AcademyInfoBean.SchoolInfoBean data) {
        mDataBinding.setAcademyInfoBean(data);
    }

    @Override
    public void setupPagePic(List<AcademyInfoBean.GraduateinfoBean.SchoolpitcureBean> bean) {
        mAdapter.setupData(bean);
    }

    @Override
    public void showNoPicEx() {
        mDataBinding.tvEmptyPictureEx.setVisibility(View.VISIBLE);
    }

    @Override
    public String getAcademyId() {
        return getIntent().getStringExtra("academy_id");
    }
}
