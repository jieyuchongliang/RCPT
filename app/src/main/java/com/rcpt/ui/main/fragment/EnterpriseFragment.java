package com.rcpt.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzp.statusbar.StatusBarCompat;
import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.databinding.FragmentEnterpriseBinding;
import com.rcpt.mvp.contract.EnterpriseListContract;
import com.rcpt.mvp.presenter.EnterpriseListPresenter;
import com.rcpt.observer_design.Observer;
import com.rcpt.observer_design.Subject;
import com.rcpt.ui.enterprise.EnterpriseChildFragment;
import com.rcpt.widget.SearchTitlePopWindow;

import java.util.ArrayList;
import java.util.List;


/**
 * 企业的Fragment
 */
public class EnterpriseFragment extends BaseFragment<FragmentEnterpriseBinding, EnterpriseListContract.View, EnterpriseListPresenter>
        implements EnterpriseListContract.View, View.OnClickListener, SearchTitlePopWindow.OnClickSearchListener, Subject {

    private SearchTitlePopWindow mSearchPop;

    private List<Fragment> mChildListFragment;

    public static EnterpriseFragment newInstance() {
        EnterpriseFragment fragment = new EnterpriseFragment();
        return fragment;
    }

    private boolean isLoadingSuccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isLoadingSuccess) {
            mPresenter.initFragments();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_enterprise;
    }

    @Override
    protected void initViews() {
        StatusBarCompat.setStatusBarColor(getActivity(), mTitleCompatLayout, true);
        setTitleText("企业");
        mPresenter.initFragments();
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView.setOnClickListener(this);
    }

    @Override
    protected EnterpriseListPresenter createPresenter() {
        return new EnterpriseListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindTabFragment(List<Fragment> fragmentList, List<String> titleList) {
        isLoadingSuccess = true;
        mChildListFragment = fragmentList;
        for (Fragment fragment : mChildListFragment) {//绑定数据的时候将所有fragmentList,集合中的元素添加到内容观察者。
            attach((EnterpriseChildFragment) fragment);
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setOffscreenPageLimit(fragmentList.size());
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }

    /**
     * 点击搜索按钮,重置按钮的事件处理
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_app_title_layout_search:
                mSearchPop = new SearchTitlePopWindow(getActivity());
                mSearchPop.setOnClickSearchListener(this);
                mSearchPop.showPopupWindow("", getView().getRootView());
                break;
            case R.id.cbx_app_title_layout_right:
                onClickSearch(null);
                notify(null,true);
                EnterpriseChildFragment fragment = (EnterpriseChildFragment) mChildListFragment.get(mDataBinding.viewPager.getCurrentItem());
                fragment.onRefresh();
                break;
        }

    }

    @Override
    public void onClickSearch(String searchText) {
        if (searchText == null || TextUtils.isEmpty(searchText)) {
            setTitleText("企业");
            mCbxTitleRightView.setVisibility(View.GONE);
            mSearchView.setVisibility(View.VISIBLE);
        } else {
            setTitleText("搜索内容:" + searchText);
            mCbxTitleRightView.setVisibility(View.VISIBLE);//隐藏搜索按钮
            mCbxTitleRightView.setText("重置");
            mSearchView.setVisibility(View.GONE);//显示“重置”按钮
            mCbxTitleRightView.setOnClickListener(this);//设置重置按钮的点击事件
            EnterpriseChildFragment fragment = (EnterpriseChildFragment) mChildListFragment.get(mDataBinding.viewPager.getCurrentItem());//点击搜索，回调后更新当前显示页信息。
            fragment.onRefresh();
            notify(searchText,false);//通过观察者告知所有mChildListFragment中的fragment，搜索内容已变更。进而重新加载数据。
        }
    }


    //存储观察者的集合
    private List<Observer> observerList = new ArrayList<>();

    //以下三个方法是实现被观察者接口重写的三个方法。
    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * 通知所有相关fragment，搜索内容是什么
     * @param message 搜索内容
     * @param isResetting 是否点击了重置
     */
    @Override
    public void notify(String message,boolean isResetting) {
        for (Observer observer : observerList) {
            observer.update(message,isResetting);
        }
    }
}
