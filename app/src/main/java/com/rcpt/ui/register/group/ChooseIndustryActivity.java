package com.rcpt.ui.register.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.databinding.ActivityChooseIndustryBinding;
import com.rcpt.mvp.contract.ChooseIndustryContract;
import com.rcpt.mvp.presenter.ChooseIndustryPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 行业列表的页面
 */
public class ChooseIndustryActivity extends BaseActivity<ActivityChooseIndustryBinding, ChooseIndustryContract.View,
        ChooseIndustryPresenter>
        implements ChooseIndustryContract.View {

    public static final int SELECT_MODE_MORE = 0x00000015;//多选模式
    public static final int SELECT_MODE_INFINITE = 0x00000030;//无限制

    private SimpleBindingAdapter<ChooseIndustryBean> mAdapter;
    private OnItemClickListener mOnItemClickListener;

    public static void actionStart(Fragment fragment,ArrayList<ChooseIndustryBean> selectIndustryList, boolean isSelectOne, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), ChooseIndustryActivity.class);
        intent.putParcelableArrayListExtra("seelctedList", selectIndustryList);
        intent.putExtra("isSelectOne", isSelectOne);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Fragment fragment, ArrayList<ChooseIndustryBean> selectIndustryList, int selectMode, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), ChooseIndustryActivity.class);
        intent.putParcelableArrayListExtra("seelctedList", selectIndustryList);
        intent.putExtra("select_mode", selectMode);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Activity activity, ArrayList<ChooseIndustryBean> selectIndustryList, int selectMode, int requestCode) {
        Intent intent = new Intent(activity, ChooseIndustryActivity.class);
        intent.putParcelableArrayListExtra("seelctedList", selectIndustryList);
        intent.putExtra("select_mode", selectMode);
        activity.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setupTitle() {
        setTitleText("行业列表");
        openTitleLeftView(true);
        if (!getIsSelectOne())
            setTitleRightText("保存").setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<ChooseIndustryBean> list = mPresenter.getSelectBeanList();
                    if (list.isEmpty()) {
                        showToast("至少选择一个行业");
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("result_data", (ArrayList<ChooseIndustryBean>) list);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
    }


    @Override
    public boolean getIsSelectOne() {
        return getIntent().getBooleanExtra("isSelectOne", false);
    }

    @Override
    public List<ChooseIndustryBean> getSelectedBeanList() {
        return getIntent().getParcelableArrayListExtra("seelctedList");
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_industry;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mOnItemClickListener = new OnItemClickListener(mDataBinding.recyclerView);

        mDataBinding.recyclerView.addOnItemTouchListener(mOnItemClickListener);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration
                .VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_choose_industry_list, BR.bean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getSelectedMode() {
        return getIntent().getIntExtra("select_mode", -1);
    }


    @Override
    public void bindListData(List<ChooseIndustryBean> beanList) {
        mAdapter.setupData(beanList);
    }


    @Override
    public void onItemSelectUpdate(int position) {
        mAdapter.notifyItemChanged(position);
        if (getIsSelectOne()) {
            Intent intent = new Intent();
            intent.putExtra("result_data", mAdapter.getItem(position));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected ChooseIndustryPresenter createPresenter() {
        return new ChooseIndustryPresenter();
    }


    private class OnItemClickListener extends OnRecyclerItemClickListener {
        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }
}
