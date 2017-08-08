package com.rcpt.ui.me.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.MenuBean;
import com.rcpt.databinding.ActivityCompanyRecruitInfoBinding;
import com.rcpt.mvp.contract.CompanyRecruitInfoContract;
import com.rcpt.mvp.presenter.CompanyRecruitInfoPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.MenuBuildUtils;
import com.rcpt.utils.SpaceItemDecoration;

import java.util.List;

public class CompanyRecruitInfoActivity extends BaseActivity<ActivityCompanyRecruitInfoBinding, CompanyRecruitInfoContract.View, CompanyRecruitInfoPresenter>
        implements CompanyRecruitInfoContract.View {

    private int mClickPosition;

    public static void actionStart(Context context, String recruitmentInfoId) {
        Intent intent = new Intent(context, CompanyRecruitInfoActivity.class);
        intent.putExtra("recruitment_id", recruitmentInfoId);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<MenuBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("招聘管理详情");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initMenu();
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_recruit_info;
    }

    @Override
    protected CompanyRecruitInfoPresenter createPresenter() {
        return new CompanyRecruitInfoPresenter();
    }

    /**
     * 获取招聘的id
     *
     * @return
     */
    @Override
    public String getRecruitmentInfoId() {
        return getIntent().getStringExtra("recruitment_id");
    }

    @Override
    public void initRecyclerView() {
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_company_recruit_info_menu, BR.menuBean);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<MenuBean> beanList) {
        mDataBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(mContext, beanList));
        mAdapter.setupData(beanList);
    }

    /**
     * 进入应聘人数列表的Activity
     *
     * @param jobName
     */
    @Override
    public void actionStartGoAcceptNumActivity(String jobName) {
        CompanyRecruitAcceptNumListActivity.actionStart(mContext, jobName, getRecruitmentInfoId());
    }

    @Override
    public List<MenuItemImpl> getMenuList() {
        return MenuBuildUtils.buildMenuItemList(mContext, R.menu.company_recruit_manage_menu);
    }

    /**
     * 获取点击的Item条目
     *
     * @return
     */
    @Override
    public int getClickPosition() {
        return mClickPosition;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
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
