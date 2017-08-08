package com.rcpt.ui.home.test;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.TestMajorListBean;
import com.rcpt.bean.TestMajorTypeBean;
import com.rcpt.databinding.ActivityTestMajorListBinding;
import com.rcpt.databinding.ItemLayoutTestMajorListBinding;
import com.rcpt.mvp.contract.TestMajorListContract;
import com.rcpt.mvp.presenter.TestMajorListPresenter;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.utils.ViewFactory;

import java.util.List;

/**
 * 专业知识测试的列表
 */
public class TestMajorListActivity extends BaseActivity<ActivityTestMajorListBinding, TestMajorListContract.View, TestMajorListPresenter>
        implements TestMajorListContract.View, RadioGroup.OnCheckedChangeListener, SpringView.OnFreshListener {

    private int mCheckedPosition;
    private boolean isEnd;
    private SimpleBindingAdapter<TestMajorListBean.TestlistBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("专业知识题库选择");
        openTitleLeftView(true);

        ImageView ivDrawableView = new ImageView(this);
        ivDrawableView.setImageResource(R.drawable.ic_vector_menu);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.LEFT_OF, getTitleRightView().getId());
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
        ivDrawableView.setLayoutParams(lp);
        mRlTitleLayoutParent.addView(ivDrawableView);
        ivDrawableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataBinding.dlTestMajorType.openDrawer(GravityCompat.END);
            }
        });

    }

    @Override
    protected void initViews() {
        mDataBinding.radioGroupMajorType.setOnCheckedChangeListener(this);
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_major_list;
    }

    @Override
    protected TestMajorListPresenter createPresenter() {
        return new TestMajorListPresenter();
    }

    @Override
    public void selectMajorTypeItemChecked(int position) {
        if (mDataBinding.radioGroupMajorType.getChildAt(position) != null) {
            ((RadioButton) mDataBinding.radioGroupMajorType.getChildAt(position)).setChecked(true);
        }
    }

    @Override
    public void bindMajorTypeData(List<TestMajorTypeBean> list) {
        for (TestMajorTypeBean bean : list) {
            ViewFactory.createMajorTypeTab(mContext, mDataBinding.radioGroupMajorType, bean.getPointName());
        }
    }

    @Override
    public int getMajorTypeClickPosition() {
        return mCheckedPosition;
    }

    @Override
    public void closeMajorTypeDrawer() {
        if (mDataBinding.dlTestMajorType.isDrawerOpen(GravityCompat.END)) {
            mDataBinding.dlTestMajorType.closeDrawer(GravityCompat.END);
        }
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));
        mDataBinding.springView.setListener(this);
        mAdapter = new SimpleBindingAdapter<TestMajorListBean.TestlistBean>(R.layout.item_layout_test_major_list, BR.majorListBean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, int position, TestMajorListBean.TestlistBean testlistBean) {
                super.bindingViews(holder, position, testlistBean);
                ItemLayoutTestMajorListBinding binding = (ItemLayoutTestMajorListBinding) holder.getBinding();
                binding.btnStartTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!LoginHelper.getInstance().isOnline()) {
                            actionStartActivity(LoginActivity.class);
                            showToast(getContext().getString(R.string.logo_message));
                            return;
                        }
                        TestMajorListBean.TestlistBean item = getItem(holder.getLayoutPosition());
                        TestMajorQuestionInfoActivity.actionStart(mContext, item.getId());
                    }
                });
            }
        };
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider_line_10dp));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(decoration);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<TestMajorListBean.TestlistBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
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
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButton childAt = (RadioButton) group.getChildAt(i);
            if (childAt.getId() == checkedId) {
                mCheckedPosition = i;
                mPresenter.onMajorTypeClick();
            }
        }
    }
}
