package com.rcpt.ui.me;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.databinding.FragmentBidRecordChildBinding;
import com.rcpt.mvp.contract.BidRecordChildContract;
import com.rcpt.mvp.presenter.BidRecordChildPresenter;

import java.util.List;

/**
 * 投标记录投标类型界面
 */
public class BidRecordChildFragment extends LazyFragment<FragmentBidRecordChildBinding, BidRecordChildContract.View, BidRecordChildPresenter>
        implements BidRecordChildContract.View {

    public static final String BID_PERSON = "person";
    public static final String BID_PROJECT = "project";

    private static final String BID_TYPE = "bid_type";

    public static BidRecordChildFragment newInstance(String bidType) {
        BidRecordChildFragment fragment = new BidRecordChildFragment();
        Bundle args = new Bundle();
        args.putString(BID_TYPE, bidType);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentAdapter mAdapter;
    private String mBidType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBidType = getArguments().getString(BID_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bid_record_child;
    }

    /**
     * 获取投标的类型
     *
     * @return
     */
    @Override
    public String getBidType() {
        return mBidType;
    }

    @Override
    protected void initViews() {
        mAdapter = new FragmentAdapter(getChildFragmentManager());
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.index.bindViewPageAndTitle(mDataBinding.viewPager, mDataBinding.radioGroup);
    }

    @Override
    protected void loadData() {
        mPresenter.initFragments();
    }

    @Override
    public void bindTabFragment(List<Fragment> fragmentList, List<String> titleList) {
        mAdapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    protected BidRecordChildPresenter createPresenter() {
        return new BidRecordChildPresenter();
    }
}
