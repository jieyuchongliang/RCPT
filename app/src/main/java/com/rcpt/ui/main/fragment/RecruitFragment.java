package com.rcpt.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.RecruitFragmentAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.bean.User;
import com.rcpt.databinding.FragmentRecruitBinding;
import com.rcpt.mvp.contract.RecruitContract;
import com.rcpt.mvp.presenter.RecruitPresenter;

import java.util.List;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;


/**
 * 招聘的Fragment
 */
public class RecruitFragment extends LazyFragment<FragmentRecruitBinding, RecruitContract.View, RecruitPresenter>
        implements RecruitContract.View, RecruitFragmentAdapter.OnItemClickListener {

    private int mItemClickPosition;
    private int mItemViewType;

    private RecruitFragmentAdapter mAdapter;
    private boolean isLoadingSuccess;


    public static RecruitFragment newInstance() {
        RecruitFragment fragment = new RecruitFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recruit;
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initViews() {
        //setTitleText("招聘");
        mDataBinding.setPresenter(mPresenter);


        User user = LoginHelper.getInstance().getUserBean();
        int hintTextRes;

        String hint = "";
        String keyword = "";
        if (Constant.UserType.PERSON.getValue().equals(user.getUserType())) {
            keyword = "职位";
            hint = getString(R.string.recruit_job_hint);
            hintTextRes = R.string.search_job_hint;
        } else {
            keyword = "人才";
            hint = getString(R.string.recruit_cv_hint);
            hintTextRes = R.string.search_cv_hint;
        }
        int keywordIndex = hint.indexOf(keyword);
        mDataBinding.tvSearchHint.setHint(hintTextRes);
        SpannableString span = new SpannableString(hint);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorOrange)), keywordIndex, keywordIndex + keyword.length(), SPAN_INCLUSIVE_INCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(22, true), keywordIndex, keywordIndex + keyword.length(), SPAN_INCLUSIVE_INCLUSIVE);
        mDataBinding.tvTitleHint.setText(span);
    }

    @Override
    protected RecruitPresenter createPresenter() {
        return new RecruitPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isLoadingSuccess) {
            mPresenter.loadListData();
        }
    }

    /**
     * 获取当前点击的条目位置
     *
     * @return
     */
    @Override
    public int getClickItemPosition() {
        return mItemClickPosition;
    }

    /**
     * 获取点击条目的类型
     *
     * @return
     */
    @Override
    public int getClickItemType() {
        return mItemViewType;
    }

    /**
     * 获取被Adapter处理过的数据源
     *
     * @return
     */
    @Override
    public List<RecruitFragmentListBean.SubBean> getAdapterListData() {
        return mAdapter.getAdapterListData();
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

        });
        mAdapter = new RecruitFragmentAdapter();
        mAdapter.setItemClickListener(this);
    }

    /*  @Override
      public void onItemClick(RecruitFragmentListBean bean, int position) {
      }
      @Override
      public void addItemClickListener(OnRecyclerItemClickListener listener) {

      }*/
    @Override
    public void bindListData(List<RecruitFragmentListBean> beanList) {
        isLoadingSuccess = true;
        mAdapter.setupData(beanList);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void onItemClick(View v, int position, int itemViewType) {
        mItemClickPosition = position;
        mItemViewType = itemViewType;
        mPresenter.onItemClick();
    }
}
