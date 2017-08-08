package com.rcpt.adapter;

import android.support.annotation.IntDef;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.databinding.ItemLayoutRecruiFilterListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/4/12.
 * 类描述：筛选器列表适配器
 */

public class FilterListAdapter extends BindingBaseRecycleAdapter<FilterSelectBean, ItemLayoutRecruiFilterListBinding> {
    /**
     * 点击的模式
     */
    //自动点击选择 只能选择1条
    public static final int CLICK_MODE_AUTOMATIC_ONE = 1;
    //手动点击选择 只能选择1条 (且不能有取消的情况)
    public static final int CLICK_MODE_MANUAL_ONE = 5;
    //手动点击选择 指定选择的条数
    public static final int CLICK_MODE_MANUAL_FINITE = 10;
    //手动点击选择 无限制
    public static final int CLICK_MODE_MANUAL_INFINITE = 15;
    //默认选择的条目数量
    private static final int DEFAULT_VALUE_MAX_SELECT_NUM = 5;

    private int mMaxSelectNum = DEFAULT_VALUE_MAX_SELECT_NUM;

    @IntDef({CLICK_MODE_AUTOMATIC_ONE, CLICK_MODE_MANUAL_FINITE, CLICK_MODE_MANUAL_ONE, CLICK_MODE_MANUAL_INFINITE})
    public @interface ClickMode {
    }

    private int mClickMode;
    private boolean isAutomatic;
    private List<FilterSelectBean> mSelectFilterList;

    private OnAutoSelectDataCallback mAutoCallback;
    private OnSelectDataCallback mCallback;

    public FilterListAdapter() {
        super(R.layout.item_layout_recrui_filter_list);
    }

    public FilterListAdapter(@ClickMode int clickMode) {
        super(R.layout.item_layout_recrui_filter_list);
        initClickMode(clickMode);
    }

    private void initClickMode(@ClickMode int clickMode) {
        mClickMode = clickMode;
        if (mClickMode == CLICK_MODE_AUTOMATIC_ONE)
            isAutomatic = true;
        else if (mClickMode != CLICK_MODE_MANUAL_ONE) {
            mSelectFilterList = new ArrayList<>();
        }
    }

    public void setClickMode(@ClickMode int clickMode) {
        initClickMode(clickMode);
    }

    /**
     * 设置最大的选择数量
     * 如果你的{@link #mClickMode}设置为了{@link #CLICK_MODE_MANUAL_FINITE}模式
     * 那么就需要调用此方法设置最大选择数量，如果不设置，就会自动设置为默认值
     *
     * @param selectNum
     */
    public void setMaxSelectNum(int selectNum) {
        if (selectNum <= 0)
            return;
        mMaxSelectNum = selectNum;
    }

    /**
     * 设置选择模式为自动选择的回调接口（单选）
     *
     * @param mAutoCallback
     */
    public void setOnAutoSelectCallback(OnAutoSelectDataCallback mAutoCallback) {
        this.mAutoCallback = mAutoCallback;
    }

    /**
     * 设置选择模式为手动选择的回调接口（单选）
     *
     * @param mCallback
     */
    public void setOnSelectDataCallback(OnSelectDataCallback mCallback) {
        this.mCallback = mCallback;
    }

    public List<FilterSelectBean> getSelectFilterList() {
        return mSelectFilterList;
    }

    @Override
    public void setupData(List<FilterSelectBean> listData) {
        super.setupData(listData);
        if (listData != null && !listData.isEmpty() && mSelectFilterList != null)
            for (FilterSelectBean selectBean : listData) {
                if (selectBean.isSelect())
                    mSelectFilterList.add(selectBean);
            }
    }

    @Override
    protected void bindingViews(BindingViewHolder<ItemLayoutRecruiFilterListBinding> holder, final int position, final FilterSelectBean filterSelectBean) {
        holder.getBinding().setValue(filterSelectBean);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mClickMode) {
                    case CLICK_MODE_MANUAL_ONE:
                    case CLICK_MODE_AUTOMATIC_ONE:
                        int cancelPosition = cancelPitchOn(position);
                        if (cancelPosition >= 0) {
                            notifyItemChanged(cancelPosition);
                            filterSelectBean.setSelect(true);
                            if (isAutomatic) {
                                if (mAutoCallback != null) {
                                    mAutoCallback.onAutoCallback(filterSelectBean);
                                }
                            } else {
                                if (mCallback != null) {
                                    mCallback.onCallback(filterSelectBean, true, true);
                                }
                            }
                        } else return;
                        break;
                    case CLICK_MODE_MANUAL_FINITE:
                    case CLICK_MODE_MANUAL_INFINITE:
                        if (filterSelectBean.isSelect()) {
                            filterSelectBean.setSelect(false);
                            mSelectFilterList.remove(filterSelectBean);
                        } else {
                            if (mClickMode == CLICK_MODE_MANUAL_FINITE && mSelectFilterList.size() >= mMaxSelectNum) {
                                if (mCallback != null) {
                                    mCallback.onCallback(null, false, true);
                                }
                                return;
                            }
                            filterSelectBean.setSelect(true);
                            if (mCallback != null) {
                                mCallback.onCallback(filterSelectBean, false, false);
                            }
                            mSelectFilterList.add(filterSelectBean);
                        }
                        break;
                }
                notifyItemChanged(position);
            }
        });
    }

    /**
     * 循环判断列表中的每个条目是否有已经选中的条目
     * 并根据当前的点击条目，判断当前选择的条目是否是已经选中的
     * 如果选中的条目不是当前的点击的条目则将选中状态设置为未选中状态，并将条目的position返回
     *
     * @param clickPosition
     * @return
     */
    private int cancelPitchOn(int clickPosition) {
        for (int i = 0; i < getItemCount(); i++) {
            FilterSelectBean item = getItem(i);
            if (item.isSelect()) {
                if (i == clickPosition) {
                    if (mAutoCallback != null) {
                        mAutoCallback.onReselectedCallback(item, i);
                    }
                    return -1;
                }
                item.setSelect(false);
                return i;
            }
        }
        return 0;
    }

    public void dataReset() {
        if (mSelectFilterList != null) {
            mSelectFilterList.clear();
        }
        if (mListData != null && !mListData.isEmpty()) {
            for (FilterSelectBean dataBean : mListData) {
                dataBean.setSelect(false);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 当自动选择的数据选择完成后的回调方法
     */
    public interface OnAutoSelectDataCallback {
        /**
         * 返回选择的数据对象
         *
         * @param bean
         */
        void onAutoCallback(FilterSelectBean bean);

        /**
         * 当条目再次选中后的回调
         *
         * @param bean
         */
        void onReselectedCallback(FilterSelectBean bean, int position);
    }

    /**
     * 当手动选择的数据选择完成后的回调方法
     */
    public interface OnSelectDataCallback {
        /**
         * 返回选择的数据对象
         *
         * @param bean             当前选中的对象
         * @param isSelectOne      是否是单选
         * @param isSelectOverflow 是否超出最大选择的数量
         */
        void onCallback(FilterSelectBean bean, boolean isSelectOne, boolean isSelectOverflow);
    }

}
