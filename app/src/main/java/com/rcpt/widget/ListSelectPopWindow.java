package com.rcpt.widget;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.rcpt.R;

import java.util.List;

/**
 * Created by lvzp on 2017/3/17.
 * 类描述：这是一个父类的列表选择Pop
 */

public abstract class ListSelectPopWindow<T> extends TextTitlePopWindow {

    protected RecyclerView mRecyclerView;
    protected OnListSelectDataCallback mCallback;
    protected String mSelectItemName;//已选中的Item名称
    /**
     * 初始化一个PopupWindow
     *
     * @param context 上下文对象
     */
    public ListSelectPopWindow(Activity context) {
        super(context, R.layout.layout_pop_list_select);
        setRightVisibly(false);
        setAnimationStyle(android.R.style.Animation_Activity);
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.recycler_view);

    }
    public void setSelectItemName(String selectItemName) {
        mSelectItemName = selectItemName;
    }

    public abstract void bindListData(List<T> list);

    public void setOnListSelectCallback(OnListSelectDataCallback callback) {
        mCallback = callback;
    }

    /**
     * 列表的Item被选中后的回调接口
     */
    public interface OnListSelectDataCallback {
        /**
         * 当条目被点击是的回调方法
         * @param data 被选中的Item名称
         * @param position 被选中Item的Position
         */
        void onSelectCallback(String data, int position);
    }
}
