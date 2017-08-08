package com.rcpt.ui.recruit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rcpt.BR;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CVPreviewInfoBean;
import com.rcpt.databinding.ActivityRecruitCvPreviewInfoBinding;
import com.rcpt.mvp.contract.RecruitCVPreviewInfoContract;
import com.rcpt.mvp.presenter.RecruitCVPreviewInfoPresenter;

import java.util.List;

/**
 * 招聘的简历详情页面
 */
public class RecruitCVPreviewInfoActivity extends BaseActivity<ActivityRecruitCvPreviewInfoBinding, RecruitCVPreviewInfoContract.View, RecruitCVPreviewInfoPresenter>
        implements RecruitCVPreviewInfoContract.View {



    public static void actionStart(Context context, String cvId, String cvUserId) {
        Intent intent = new Intent(context, RecruitCVPreviewInfoActivity.class);
        intent.putExtra("cv_id", cvId);
        intent.putExtra("cv_user_id", cvUserId);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<CVPreviewInfoBean.EdiucationpdListBean> mEducationListAdapter;
    private SimpleBindingAdapter<CVPreviewInfoBean.ExperiencepdListBean> mWorkExListAdapter;
    private SimpleBindingAdapter<CVPreviewInfoBean.ProjectpdListBean> mProjectExListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("简历详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        if (getUserType().equals(Constant.UserType.PERSON.getValue())) {
            mDataBinding.llHead.setVisibility(View.GONE);
        }
        initListViews();
        mPresenter.loadPageData();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recruit_cv_preview_info;
    }

    @Override
    protected RecruitCVPreviewInfoPresenter createPresenter() {
        return new RecruitCVPreviewInfoPresenter();
    }

    /**
     * 用户类型
     *
     * @return
     */
    @Override
    public String getUserType() {
        return LoginHelper.getInstance().getUserBean().getUserType();
    }

    /**
     * 获取简历的Id
     *
     * @return
     */
    @Override
    public String getCVId() {
        return getIntent().getStringExtra("cv_id");
    }

    /**
     * 获取简历用户ID
     *
     * @return
     */
    @Override
    public String getCVUserId() {
        return getIntent().getStringExtra("cv_user_id");
    }

    /**
     * 绑定页面基本数据
     *
     * @param infoBean
     */
    @Override
    public void bindPageData(CVPreviewInfoBean infoBean) {
        mDataBinding.setInfoBean(infoBean);
    }

    /**
     * 绑定教育背景列表数据
     *
     * @param educationList
     */
    @Override
    public void bindEducationListData(List<CVPreviewInfoBean.EdiucationpdListBean> educationList) {
        mEducationListAdapter.setupData(educationList);
    }

    /**
     * 绑定工作经验列表数据
     *
     * @param workExList
     */
    @Override
    public void bindWorkExListData(List<CVPreviewInfoBean.ExperiencepdListBean> workExList) {
        mWorkExListAdapter.setupData(workExList);
    }

    /**
     * 绑定项目经验数据列表
     *
     * @param projectExList
     */
    @Override
    public void bindProjectExListData(List<CVPreviewInfoBean.ProjectpdListBean> projectExList) {
        mProjectExListAdapter.setupData(projectExList);
    }

    /**
     * 显示教育背景未添加的空布局
     */
    @Override
    public void showEducationEmptyView() {
        mDataBinding.tvEmptyEducation.setVisibility(View.VISIBLE);
    }

    /**
     * 显示工作经历未添加的空布局
     */
    @Override
    public void showWorkExEmptyView() {
        mDataBinding.tvEmptyWorkEx.setVisibility(View.VISIBLE);
    }

    /**
     * 显示工作经验未添加的空布局
     */
    @Override
    public void showProjectExEmptyView() {
        mDataBinding.tvEmptyProjectEx.setVisibility(View.VISIBLE);
    }

    /**
     * 改变收藏的状态
     *
     * @param isCollect 是否为收藏
     */
    @Override
    public void changeCollectStatus(boolean isCollect) {
        if (isCollect) {
            mDataBinding.ivCollect.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_vector_cv_collect));
        } else {
            mDataBinding.ivCollect.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_vector_cv_un_collect));
        }
    }

    /**
     * 初始化列表的布局控件
     */
    private void initListViews() {

        setRecyclerViewLayoutManager(mDataBinding.educationRecyclerView, mDataBinding.projectExRecyclerView, mDataBinding.workExRecyclerView);

        mEducationListAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_recruit_cv_preview_info_education_list, BR.educationBean);
        mWorkExListAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_recruit_cv_preview_info_work_ex_list, BR.workExBean);
        mProjectExListAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_recruit_cv_preview_info_project_ex_list, BR.projectBean);

        mDataBinding.educationRecyclerView.setAdapter(mEducationListAdapter);
        mDataBinding.workExRecyclerView.setAdapter(mWorkExListAdapter);
        mDataBinding.projectExRecyclerView.setAdapter(mProjectExListAdapter);
    }

    /**
     * 为RecyclerView绑定布局管理器
     *
     * @param recyclerViews
     */
    private void setRecyclerViewLayoutManager(RecyclerView... recyclerViews) {
        //由于LayoutManager只能绑定到一个RecyclerView中所有需要这么去做
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }

    }

}
