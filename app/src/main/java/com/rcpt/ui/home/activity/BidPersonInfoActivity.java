package com.rcpt.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.BidPersonInfoBean;
import com.rcpt.databinding.ActivityBidPersonInfoBinding;
import com.rcpt.mvp.contract.BidPersonInfoContract;
import com.rcpt.mvp.presenter.BidPersonInfoPresenter;

public class BidPersonInfoActivity extends BaseActivity<ActivityBidPersonInfoBinding,BidPersonInfoContract.View, BidPersonInfoPresenter>
        implements BidPersonInfoContract.View{


    private static final String BID_ID_TAG = "bid_id";

    public static void actionStart(Context context, String bidId) {
        Intent intent = new Intent(context, BidPersonInfoActivity.class);
        intent.putExtra(BID_ID_TAG, bidId);
        context.startActivity(intent);
    }

    private String mBidId;

    @Override
    protected void setupTitle() {
        setTitleText("人员详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mBidId = getIntent().getStringExtra(BID_ID_TAG);
        mPresenter.loadPersonInfo();
        mDataBinding.setPresenter(mPresenter);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bid_person_info;
    }

    @Override
    protected BidPersonInfoPresenter createPresenter() {
        return new BidPersonInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    @Override
    public String getBidPersonId() {
        return mBidId;
    }

    @Override
    public void setBean(BidPersonInfoBean bean) {
        if (bean.getItSkill_1() == null) {
            bean.setItSkillTxt(null);
        } else if (bean.getItSkill_2() == null) {
            bean.setItSkillTxt(bean.getItSkill_1());
        } else if (bean.getItSkill_3() == null) {
            bean.setItSkillTxt(bean.getItSkill_1() + "、" + bean.getItSkill_2());
        } else if (bean.getItSkill_4() == null) {
            bean.setItSkillTxt(bean.getItSkill_1() + "、" + bean.getItSkill_2() + "、" + bean.getItSkill_3());
        } else if (bean.getItSkill_5() == null) {
            bean.setItSkillTxt(bean.getItSkill_1() + "、" + bean.getItSkill_2() + "、" + bean.getItSkill_3()
                    + "、" + bean.getItSkill_4());
        } else {
            bean.setItSkillTxt(bean.getItSkill_1() + "、" + bean.getItSkill_2() + "、" + bean.getItSkill_3()
                    + "、" + bean.getItSkill_4() + "、" + bean.getItSkill_5());
        }

        if (bean.getIndustryKnowledge_1() == null) {
            bean.setIndustryKnowledgeTxt(null);
        } else if (bean.getIndustryKnowledge_2() == null) {
            bean.setIndustryKnowledgeTxt(bean.getIndustryKnowledge_1());
        } else if (bean.getIndustryKnowledge_3() == null) {
            bean.setIndustryKnowledgeTxt(bean.getIndustryKnowledge_1() + "、" + bean.getIndustryKnowledge_2());
        } else if (bean.getIndustryKnowledge_4() == null) {
            bean.setIndustryKnowledgeTxt(bean.getIndustryKnowledge_1() + "、" + bean.getIndustryKnowledge_2() + "、" + bean.getIndustryKnowledge_3());
        } else if (bean.getIndustryKnowledge_5() == null) {
            bean.setIndustryKnowledgeTxt(bean.getIndustryKnowledge_1() + "、" + bean.getIndustryKnowledge_2() + "、" + bean.getIndustryKnowledge_3()
                    + "、" + bean.getIndustryKnowledge_4());
        } else {
            bean.setIndustryKnowledgeTxt(bean.getIndustryKnowledge_1() + "、" + bean.getIndustryKnowledge_2() + "、" + bean.getIndustryKnowledge_3()
                    + "、" + bean.getIndustryKnowledge_4() + "、" + bean.getIndustryKnowledge_5());
        }
        mDataBinding.setBean(bean);
    }

    /**
     * 界面不可见的时候设置
     */
    @Override
    protected void onPause() {
        super.onPause();
        //界面不可见时将竞标是否成功的标记置为false
        mPresenter.isBidSuccess(null);
    }
}
