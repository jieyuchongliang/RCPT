package com.rcpt.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.rcpt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 860617003 on 2017/5/24.
 */

public class FilterItemView extends LinearLayout implements View.OnClickListener {

    private static final int DEFAULT_VALUE_PADDING = 6;
    private static final int DEFAULT_VALUE_MARGIN = 10;
    private static final int DEFAULT_VALUE_BUTTON_SIZE = 20;

    private TextView mTvFilterName;
    private LinearLayout mSelectValueLayout;

    private OnClickCloseListener mOnClickCloseListener;
    private DataHolder mDataHolder;

    private int mBindFilterViewId;
    private boolean isNeedClose;

    public FilterItemView(Context context) {
        this(context, null);
    }

    public FilterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultValue();
        init(attrs);
    }

    private void initDefaultValue() {

        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_VALUE_PADDING, getResources().getDisplayMetrics());

        setOrientation(LinearLayout.HORIZONTAL);
        setPadding((int) padding + getDip(10), (int) padding, (int) padding + getDip(5), (int) padding);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTvFilterName = new TextView(getContext());

        mSelectValueLayout = new LinearLayout(getContext());
        mSelectValueLayout.setOrientation(LinearLayout.VERTICAL);

        mTvFilterName.setLayoutParams(lp);
        mSelectValueLayout.setLayoutParams(lp);
        mSelectValueLayout.setGravity(Gravity.CENTER_VERTICAL);

        addView(mTvFilterName);
        addView(mSelectValueLayout);

        setBackgroundResource(R.drawable.bg_solid_white);
        ViewCompat.setElevation(this, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
    }

    private void init(AttributeSet attrs) {

    }


    private int getDip(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    private void setFilterName(String filterName) {
        if (filterName != null)
            mTvFilterName.setText(filterName);
    }

    private void setFilterSelectValue(String filterSelectValue, String oldValue, boolean isUpdate, boolean isNeedClose) {
        if (filterSelectValue != null) {
            if (isUpdate) {
                updateFilterItem(filterSelectValue, oldValue);
            } else {
                addFilterChildSelectValue(filterSelectValue, isNeedClose, false);
            }
        }
    }

    private void addFilterChildSelectValue(String value, boolean isNeedClose, boolean isChild) {

        LinearLayout linearLayout = new LinearLayout(getContext());
        TextView tvSelectValue = new TextView(getContext());
        //设置TextView的布局属性
        LinearLayout.LayoutParams tvSelectLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvSelectLayoutParams.leftMargin = getDip(5);
        tvSelectLayoutParams.weight = 1;
        //设置筛选条件的属性
        tvSelectValue.setTextColor(Color.parseColor("#FF333333"));
        tvSelectValue.setMaxLines(1);
        tvSelectValue.setText(value);
        tvSelectValue.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        tvSelectValue.setLayoutParams(tvSelectLayoutParams);
        //将显示的文本，放入到布局列表中
        linearLayout.addView(tvSelectValue);

        //设置button的属性
        if (isNeedClose) {
            ImageButton btnClose = new ImageButton(getContext());
            btnClose.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(getDip(DEFAULT_VALUE_BUTTON_SIZE), getDip(DEFAULT_VALUE_BUTTON_SIZE));
            btnLayoutParams.leftMargin = getDip(25);
            btnClose.setLayoutParams(btnLayoutParams);
            btnClose.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_vector_white_close));
            btnClose.setBackgroundResource(R.drawable.bg_close);
            btnClose.setOnClickListener(this);
            btnClose.setTag(value);
            linearLayout.addView(btnClose);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (isChild) {
            lp.topMargin = getDip(5);
        }
        if (mDataHolder.child != null && !mDataHolder.child.isEmpty())
            lp.bottomMargin = getDip(5);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setLayoutParams(lp);

        mSelectValueLayout.addView(linearLayout);
    }

    private void updateFilterItem(String value, String oldValue) {
        int childCount = mSelectValueLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View groupView = mSelectValueLayout.getChildAt(i);
            if (groupView instanceof ViewGroup) {
                int groupChildCount = ((ViewGroup) groupView).getChildCount();
                for (int j = 0; j < groupChildCount; j++) {
                    View childView = ((ViewGroup) groupView).getChildAt(j);
                    if (childView instanceof TextView) {
                        String selectValue = ((TextView) childView).getText().toString();
                        if (!TextUtils.isEmpty(selectValue) && selectValue.equals(oldValue)) {
                            ((TextView) childView).setText(value);
                        }
                    }
                    if (childView instanceof ImageButton) {
                        childView.setTag(value);
                    }
                }
            }
        }
    }


    public void setOnClickCloseListener(OnClickCloseListener listener) {
        mOnClickCloseListener = listener;
    }

    public int getBindFilterViewId() {
        return mBindFilterViewId;
    }

    public boolean isNeedClose() {
        return isNeedClose;
    }

    /**
     * 将数据绑定到View中
     *
     * @param holder
     * @param isNeedClose
     */
    public void bindFilterData(DataHolder holder, boolean isNeedClose) {
        DataHolder oldHolder = mDataHolder;
        boolean isUpdate = false;
        String oldValue = null;
        if (oldHolder != null) {
            isUpdate = oldHolder.getFilterViewId() == holder.getFilterViewId();
            oldValue = oldHolder.filterSelectValue;
        }
        mDataHolder = holder;
        this.isNeedClose = isNeedClose;
        if (mDataHolder != null) {
            mBindFilterViewId = mDataHolder.getFilterViewId();
            setFilterName(mDataHolder.filterName);
            setFilterSelectValue(mDataHolder.filterSelectValue, oldValue, isUpdate, isNeedClose);
            if (mDataHolder.child != null && !mDataHolder.child.isEmpty()) {
                if (isUpdate) {
                    //清除除父类型的其它子类型的布局
                    while (mSelectValueLayout.getChildCount() > 1) {
                        mSelectValueLayout.removeViewAt(1);
                    }
                }
                for (String s : mDataHolder.child) {
                    addFilterChildSelectValue(s, true, true);
                }
            }
        }
    }


    /**
     * 获取当前控件的Margin值
     *
     * @return
     */
    public int getMargin() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_VALUE_MARGIN, getResources().getDisplayMetrics());
    }

    /**
     * 获取绑定的DataHolder
     *
     * @return
     */
    public DataHolder getDataHolder() {
        return mDataHolder;
    }

    public static class DataHolder {
        View filterView;
        String filterName;
        String filterSelectValue;
        List<String> child;

        public static DataHolder obtain(View filterView, String filterName, String filterSelectValue, String... child) {
            DataHolder holder = new DataHolder();
            holder.filterView = filterView;
            holder.filterName = filterName;
            holder.filterSelectValue = filterSelectValue;
            if (child != null) {
                holder.child = new ArrayList<>(Arrays.asList(child));
            }
            return holder;
        }

        public int getFilterViewId() {
            return filterView.getId();
        }

    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        String value;
        if (tag != null) {
            value = String.valueOf(tag);
            if (value.equals(mDataHolder.filterSelectValue)) {
                if (mOnClickCloseListener != null) {
                    mOnClickCloseListener.onClickParentClose(mDataHolder);
                }
            } else {
                if (mOnClickCloseListener != null) {
                    mOnClickCloseListener.onClickChildClose(mDataHolder, value);
                }
                int position = 0;
                for (int i = 0; i < mDataHolder.child.size(); i++) {
                    if (mDataHolder.child.get(i).equals(value)) {
                        position = i;
                        break;
                    }
                }
                mSelectValueLayout.removeViewAt(position + 1);
                mDataHolder.child.remove(value);
                if (mDataHolder.child.size() == 0) {
                    if (mOnClickCloseListener != null) {
                        mOnClickCloseListener.onClickParentClose(mDataHolder);
                    }
                }
            }
        }

    }

    public interface OnClickCloseListener {
        /**
         * 点击关闭（删除）筛选的回调
         *
         * @param holder 条件筛选内容的绑定数据
         */
        void onClickParentClose(DataHolder holder);

        /**
         * 当子类型关闭（删除）筛选的回调
         *
         * @param parentHolder
         * @param childName
         */
        void onClickChildClose(DataHolder parentHolder, String childName);
    }

}
