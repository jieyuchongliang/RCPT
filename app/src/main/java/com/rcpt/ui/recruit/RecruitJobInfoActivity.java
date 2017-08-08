package com.rcpt.ui.recruit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.RecruitJobInfoBean;
import com.rcpt.databinding.ActivityRecruitJobInfoBinding;
import com.rcpt.mvp.contract.RecruitInfoContract;
import com.rcpt.mvp.presenter.RecruitInfoPresenter;

/**
 * 职位详情页面
 */
public class RecruitJobInfoActivity extends BaseActivity<ActivityRecruitJobInfoBinding, RecruitInfoContract.View, RecruitInfoPresenter>
        implements RecruitInfoContract.View {

    private static final String ID_TAG = "id";
    private static final String COMPANY_ID_TAG = "companyId";

    /**
     * @param context
     * @param companyId 公司id
     * @param id        详情id
     */
    public static void actionStart(Context context, String companyId, String id) {
        Intent intent = new Intent(context, RecruitJobInfoActivity.class);
        intent.putExtra(ID_TAG, id);
        intent.putExtra(COMPANY_ID_TAG, companyId);
        context.startActivity(intent);
    }

    @Override
    protected void setupTitle() {
        setTitleText("职位详情");
        openTitleLeftView(true);
    }

    @Override
    public String getCompanyId() {
        return getIntent().getStringExtra(COMPANY_ID_TAG);
    }

    @Override
    public String getJobId() {
        return getIntent().getStringExtra(ID_TAG);
    }

    @Override
    protected void initViews() {
        mPresenter.loadInfo();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recruit_job_info;
    }

    @Override
    protected RecruitInfoPresenter createPresenter() {
        return new RecruitInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupData(RecruitJobInfoBean bean) {
        mDataBinding.setInfoBean(bean);
    }

    /**
     * 设置投递简历的按钮为不可点击状态
     */
    @Override
    public void setSendResumeBtnClickOff() {
        int padding = mDataBinding.btnSendResume.getPaddingLeft();
        mDataBinding.btnSendResume.setEnabled(false);
        mDataBinding.btnSendResume.setText("已投递简历");
        ViewCompat.setBackground(mDataBinding.btnSendResume, ContextCompat.getDrawable(mContext, R.drawable.bg_gray_btn));
        mDataBinding.btnSendResume.setPadding(padding, 0, padding, 0);
    }

    /**
     * 设置收藏按钮为收藏状态
     */
    @Override
    public void setClickFavoriteChecked() {
        mDataBinding.ivFollow.setBackgroundColor(Color.WHITE);
        mDataBinding.ivFollow.setImageResource(R.drawable.ic_vector_follow);
    }

    /**
     * 设置收藏按钮为未收藏状态
     */
    @Override
    public void setClickFavoriteUnChecked() {
        ViewCompat.setBackground(mDataBinding.ivFollow, ContextCompat.getDrawable(mContext, R.drawable.bg_blue_round_corners));
        mDataBinding.ivFollow.setImageResource(R.mipmap.ic_recruit_follow);
    }

    public void showSnackbar(String message) {
        Snackbar.make(mDataBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

}
