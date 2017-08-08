package com.rcpt.ui.me.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CompanyRecruitListBean;
import com.rcpt.databinding.ActivityCompanyRecruitBinding;
import com.rcpt.mvp.contract.CompanyRecruitContract;
import com.rcpt.mvp.presenter.CompanyRecruitPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.recruit.SendJobInvitationActivity;

import java.util.List;

public class CompanyRecruitActivity extends BaseActivity<ActivityCompanyRecruitBinding, CompanyRecruitContract.View, CompanyRecruitPresenter>
        implements CompanyRecruitContract.View, SpringView.OnFreshListener {

    public static final String FROM_WHERE_TAG_ME = "me";
    public static final String FROM_WHERE_TAG_CV_INFO = "cv_info";

    private static final int REQUEST_CODE_SEND_JOB_INVITATION = 0x0000112;

    public static void actionStart(Context context, String fromWhere, String cvId, String cvUserId) {
        Intent intent = new Intent(context, CompanyRecruitActivity.class);
        intent.putExtra("from_where", fromWhere);
        intent.putExtra("cv_id", cvId);
        intent.putExtra("cv_user_id", cvUserId);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<CompanyRecruitListBean.RecruitlistBean> mAdapter;
    private boolean isEnd;
    private int mClickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        String titleText = "";
        switch (getFromWhere()) {
            case FROM_WHERE_TAG_CV_INFO:
                titleText = "招聘列表选取";
                break;
            case FROM_WHERE_TAG_ME:
                titleText = "招聘管理";
                break;
        }
        setTitleText(titleText);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_recruit;
    }


    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_company_recruit_list, BR.bean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<CompanyRecruitListBean.RecruitlistBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
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
    protected CompanyRecruitPresenter createPresenter() {
        return new CompanyRecruitPresenter();
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 开始进入到招聘详情
     */
    @Override
    public void startGoRecruitInfo(String id) {
        CompanyRecruitInfoActivity.actionStart(mContext, id);
    }

    /**
     * 进入到发布职位邀请界面
     */
    @Override
    public void startGoSendJobInvitation(CompanyRecruitListBean.RecruitlistBean bean, String cvId) {
        SendJobInvitationActivity.actionStart(this, bean, cvId, getCVUserId(), REQUEST_CODE_SEND_JOB_INVITATION);
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
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
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

    /**
     * 获取简历的id
     *
     * @return
     */
    @Override
    public String getCVId() {
        return getIntent().getStringExtra("cv_id");
    }

    @Override
    public String getCVUserId() {
        return getIntent().getStringExtra("cv_user_id");
    }

    /**
     * 获取来自于哪里
     *
     * @return
     */
    @Override
    public String getFromWhere() {
        return getIntent().getStringExtra("from_where");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SEND_JOB_INVITATION:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }
    public void showSnackbar(String msg){
        Snackbar.make(mDataBinding.getRoot(),msg,Snackbar.LENGTH_SHORT).show();
    }
    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mClickPosition = vh.getLayoutPosition();
            mPresenter.onItemClick();
        }
    }

}
