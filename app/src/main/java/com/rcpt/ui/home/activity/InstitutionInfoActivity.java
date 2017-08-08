package com.rcpt.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.R;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.InstitutionDetailBean;
import com.rcpt.databinding.ActivityInstitutionInfoBinding;
import com.rcpt.mvp.contract.InstitutionInfoContract;
import com.rcpt.mvp.presenter.InstitutionInfoPresenter;

import java.util.List;

public class InstitutionInfoActivity extends BaseActivity<ActivityInstitutionInfoBinding, InstitutionInfoContract.View, InstitutionInfoPresenter>
        implements InstitutionInfoContract.View {


    private SimpleBindingAdapter<InstitutionDetailBean.EnviromentlistBean> mEnviromentAdapter;
    private SimpleBindingAdapter<InstitutionDetailBean.CourselistBean> mCourseAdapter;
    private SimpleBindingAdapter<InstitutionDetailBean.TeacherlistBean> mTeacherAdapter;

    public static void actionStartActivity(Context context, String institutionId) {
        Intent intent = new Intent(context, InstitutionInfoActivity.class);
        intent.putExtra("institutionId", institutionId);
        context.startActivity(intent);
    }

    @Override
    protected void setupTitle() {
        setTitleText("机构详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        initRecycleView();
        mPresenter.loadInfo();
    }

    private void initRecycleView() {

        mDataBinding.courseRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        mDataBinding.teacherRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        mEnviromentAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_institution_enviroment, BR.bean);
        mCourseAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_institution_course, BR.bean);
        mTeacherAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_institution_teacher, BR.bean);

        mDataBinding.courseRecyclerView.setAdapter(mCourseAdapter);
        mDataBinding.teacherRecyclerView.setAdapter(mTeacherAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_institution_info;
    }

    @Override
    protected InstitutionInfoPresenter createPresenter() {
        return new InstitutionInfoPresenter();
    }

    @Override
    public String getInstitutionId() {
        return getIntent().getStringExtra("institutionId");
    }

    @Override
    public void setUpData(InstitutionDetailBean bean) {
        mDataBinding.setBean(bean);
    }

    @Override
    public void setUpCourse(List<InstitutionDetailBean.CourselistBean> courseBeanList) {
        mCourseAdapter.setupData(courseBeanList);
    }

    @Override
    public void setUpTeacher(List<InstitutionDetailBean.TeacherlistBean> teacherBeanList) {
        mTeacherAdapter.setupData(teacherBeanList);
    }


    @Override
    public void showEmptyCourse() {
        mDataBinding.tvEmptyCourseEx.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyTeacher() {
        mDataBinding.tvEmptyTeacherEx.setVisibility(View.VISIBLE);
    }

    @Override
    public void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images) {
        if (images == null) {
            mDataBinding.environmentBanner.setVisibility(View.GONE);
        } else if (images.size() > 1){
            mDataBinding.environmentBanner.setVisibility(View.VISIBLE);
            mDataBinding.environmentBanner
                    .setPages(holderCreator, images)
                    .setPageIndicator(new int[]{R.drawable.ic_banner_page_indicator, R.drawable.ic_banner_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .startTurning(3000);
        }else {
            mDataBinding.environmentBanner
                    .setPages(holderCreator, images)
                    .setManualPageable(false);
        }

    }
}
