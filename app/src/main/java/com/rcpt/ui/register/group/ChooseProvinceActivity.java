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
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.databinding.ActivityChooseProvinceBinding;
import com.rcpt.mvp.contract.ChooseProvinceContract;
import com.rcpt.mvp.presenter.ChooseProvincePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

public class ChooseProvinceActivity extends BaseActivity<ActivityChooseProvinceBinding, ChooseProvinceContract.View,
        ChooseProvincePresenter>
        implements ChooseProvinceContract.View {

    private SimpleBindingAdapter<ChooseProvinceBean> mAdapter;
    private OnItemClickListener mOnItemClickListener;

    private String provinceId;
    private String cityId;
    private boolean isLimit;

    public static void actionStart(Activity activity, boolean isThree, int requestCode) {
        Intent intent = new Intent(activity, ChooseProvinceActivity.class);
        intent.putExtra("isThree", isThree);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Fragment fragment, boolean isThree, String provinceId, String cityId, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), ChooseProvinceActivity.class);
        intent.putExtra("isThree", isThree);
        intent.putExtra("provinceId", provinceId);
        intent.putExtra("cityId", cityId);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Activity activity, boolean isThree, boolean isLimit, String provinceId, String cityId, int requestCode) {
        Intent intent = new Intent(activity, ChooseProvinceActivity.class);
        intent.putExtra("isThree", isThree);
        intent.putExtra("provinceId", provinceId);
        intent.putExtra("cityId", cityId);
        intent.putExtra("limit", isLimit);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initFieldBeforeMethods() {
        isLimit = getIntent().getBooleanExtra("limit", false);
    }

    @Override
    protected void setupTitle() {
        setTitleText("省份列表");
        openTitleLeftView(true);
        if (isLimit) {
            setTitleRightText("不限").setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCitySelectOver(null, null, null);
                }
            });
        }
    }

    /**
     * 获取是否进行三级联动
     *
     * @return
     */
    @Override
    public boolean isThree() {
        return getIntent().getBooleanExtra("isThree", false);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_province;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mOnItemClickListener = new OnItemClickListener(mDataBinding.recyclerView);
        mDataBinding.recyclerView.addOnItemTouchListener(mOnItemClickListener);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration
                .VERTICAL));

        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_choose_province_list, BR.bean);
        mDataBinding.recyclerView.setAdapter(mAdapter);

    }

    /**
     * 当城市选择结束后回调的数据
     *
     * @param provinceBean
     * @param cityBean
     * @param areaBean
     */
    @Override
    public void onCitySelectOver(ChooseProvinceBean provinceBean, ChooseProvinceBean cityBean, ChooseProvinceBean areaBean) {
        Intent intent = new Intent();
        intent.putExtra("provinceBean", provinceBean);
        intent.putExtra("cityBean", cityBean);
        if (isThree()) {
            intent.putExtra("areaBean", areaBean);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void bindListData(List<ChooseProvinceBean> beanList) {
        provinceId = getIntent().getStringExtra("provinceId");
        cityId = getIntent().getStringExtra("cityId");
        if (beanList != null && !beanList.isEmpty()) {
            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(i).getRegionId().equals(provinceId) || beanList.get(i).getRegionId().equals(cityId)) {
                    beanList.get(i).setSelect(true);
                    mAdapter.setupData(beanList);
                    mDataBinding.recyclerView.scrollToPosition(i);
                    return;
                }
            }
            mAdapter.setupData(beanList);
            mDataBinding.recyclerView.scrollToPosition(0);
        }
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected ChooseProvincePresenter createPresenter() {
        return new ChooseProvincePresenter();
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
