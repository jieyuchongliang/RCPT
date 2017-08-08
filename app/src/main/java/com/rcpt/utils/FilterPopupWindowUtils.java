package com.rcpt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.rcpt.R;
import com.rcpt.databinding.PopupLayoutBinding;
import com.rcpt.widget.FilterItemView;


/**
 * Created by lvzp on 2017/5/24.
 * 条件筛选的工具类
 * 这个类里面内部维护了一个PopupWindow,所以在使用的时候只需要将数据添加进来就可以了
 * 使用方法：
 * 1.首先创建一个该对象的实例
 * 2.通过{@link #addFilterItem(FilterItemView.DataHolder, boolean)}方法，将选中的Item添加进来
 * 3.通过{@link #removeFilterItem(FilterItemView.DataHolder)}方法将选中的内容删除(此方法不建议外部直接调用)
 * 4.通过{@link #showPop(View)}将PopupWindow显示出来
 */

@SuppressLint("WrongConstant")
public class FilterPopupWindowUtils {

    private PopupWindow mPopupWindow;
    private PopupLayoutBinding mPopupBinding;
    private int mFilterCount;
    private Context mContext;
    private OnFilterPopupListener mOnFilterPopupListener;

    public FilterPopupWindowUtils(Context context) {
        mContext = context;
        //初始化PopupWindow
        View rootView = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);
        mPopupBinding = DataBindingUtil.bind(rootView);
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setContentView(rootView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.PopWindow_y_anim_style);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
     /*   mPopupBinding.bgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });*/
        mPopupBinding.tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupBinding.tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mPopupBinding.llFilter.getChildCount(); i++) {
                    View childView = mPopupBinding.llFilter.getChildAt(i);
                    if (childView instanceof FilterItemView) {
                        removeFilterItem(((FilterItemView) childView).getDataHolder());
                    }
                }
                if (mOnFilterPopupListener != null) {
                    mOnFilterPopupListener.onFiltersReset();
                }
            }
        });
    }

    /**
     * 获取其中内置的PopupWindow
     *
     * @return
     */
    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public void setupFilterCount(int count) {
        mFilterCount = count;
        int childCount = mPopupBinding.llFilter.getChildCount();
        mPopupBinding.tvSelectCount.setText(mContext.getString(R.string.select_num, String.valueOf(childCount), String.valueOf(count)));
    }

    public void updateFilterNum(int num) {
        mPopupBinding.tvSelectCount.setText(mContext.getString(R.string.select_num, String.valueOf(num), String.valueOf(mFilterCount)));
    }

    public void setOnFilterItemClickCloseListener(OnFilterPopupListener listener) {
        mOnFilterPopupListener = listener;
    }

    /**
     * 添加筛选条件的Item
     *
     * @param holder      绑定的数据
     * @param isNeedClose 是否需要删除按钮
     */
    public int addFilterItem(FilterItemView.DataHolder holder, boolean isNeedClose) {
        int oldChildCount = mPopupBinding.llFilter.getChildCount();
        for (int i = 0; i < oldChildCount; i++) {
            View childView = mPopupBinding.llFilter.getChildAt(i);
            if (childView instanceof FilterItemView) {
                //首先判断筛选条件中是否已经有筛选的类型
                if (holder.getFilterViewId() == ((FilterItemView) childView).getBindFilterViewId()) {
                    updateFilterItem(holder, i);
                    return oldChildCount;
                }
            }
        }
        //如果没有就创建一个新的筛选Item，并添加到条件中
        FilterItemView view = new FilterItemView(mContext);
        //将数据绑定到控件中
        view.bindFilterData(holder, isNeedClose);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(view.getMargin(), view.getMargin(), view.getMargin(), view.getMargin());
        view.setLayoutParams(lp);
        view.setOnClickCloseListener(new FilterItemView.OnClickCloseListener() {
            @Override
            public void onClickParentClose(FilterItemView.DataHolder dataHolder) {
                removeFilterItem(dataHolder);
            }

            /**
             * 当子类型关闭（删除）筛选的回调
             *
             * @param parentHolder
             * @param childName
             */
            @Override
            public void onClickChildClose(FilterItemView.DataHolder parentHolder, String childName) {
                if (mOnFilterPopupListener != null) {
                    mOnFilterPopupListener.onItemChildClickClose(parentHolder,childName);
                }
            }
        });
        insertFilterItem(view);
        int childCount = mPopupBinding.llFilter.getChildCount();
        updateFilterNum(childCount);
        return childCount;
    }

    /**
     * 去除筛选的Item
     *
     * @param holder 需要去除的数据
     */
    public void removeFilterItem(final FilterItemView.DataHolder holder) {
        if (holder == null) return;
        int childCount = mPopupBinding.llFilter.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = mPopupBinding.llFilter.getChildAt(i);
            if (childView instanceof FilterItemView) {
                if (holder.getFilterViewId() == ((FilterItemView) childView).getBindFilterViewId()) {
                    AnimationUtils.mackAlphaAnimation(childView, false).setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mPopupBinding.llFilter.removeView(childView);
                            updateFilterNum(mPopupBinding.llFilter.getChildCount());
                            if (mOnFilterPopupListener != null) {
                                mOnFilterPopupListener.onItemClickClose(holder);
                            }
                            if (mPopupBinding.llFilter.getChildCount() == 0) {
                                mPopupBinding.slFilterParent.setVisibility(View.GONE);
                                mPopupBinding.tvFilterEmpty.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    break;
                }
            }
        }
    }

    /**
     * 更新筛选的条件
     *
     * @param holder
     * @param position
     */
    private void updateFilterItem(FilterItemView.DataHolder holder, int position) {
        FilterItemView itemView = (FilterItemView) mPopupBinding.llFilter.getChildAt(position);
        itemView.bindFilterData(holder, itemView.isNeedClose());
    }

    /**
     * 插入先的筛选条件
     *
     * @param view
     */
    private void insertFilterItem(View view) {
        mPopupBinding.llFilter.addView(view);
        mPopupBinding.slFilterParent.setVisibility(View.VISIBLE);
        mPopupBinding.tvFilterEmpty.setVisibility(View.GONE);
    }

    /**
     * 显示PopupWindow
     *
     * @param view
     */
    public void showPop(View view) {
        mPopupWindow.showAsDropDown(view);
    }

    /**
     * 关闭
     */
    public void dismiss() {
        mPopupWindow.dismiss();
    }

    public interface OnFilterPopupListener {
        /**
         * 当里面的Item被点击关闭的回调
         *
         * @param holder
         */
        void onItemClickClose(FilterItemView.DataHolder holder);

        /**
         * 当筛选条件的子Item被删除的回调
         * @param holder
         * @param childName
         */
        void onItemChildClickClose(FilterItemView.DataHolder holder,String childName);

        /**
         * 当点击重置的回调接口
         */
        void onFiltersReset();
    }

}
