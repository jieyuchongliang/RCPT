package com.rcpt.base.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：吕振鹏
 * 创建时间：08月29日
 * 时间：18:32
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public abstract class BindingBaseAdapter<T, D extends ViewDataBinding> extends BaseAdapter {

    protected D mDataBinding;

    protected List<T> mListData;
    private LayoutInflater mInflater;
    private int mResLayoutId;

    public BindingBaseAdapter(Context context, int resLayoutId) {
        mResLayoutId = resLayoutId;
        mInflater = LayoutInflater.from(context);
    }

    public void bindData(List<T> listData){
        if (mListData == null)
            mListData = listData;
        else
            mListData.addAll(listData);

    }
    @Override
    public int getCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public T getItem(int i) {
        return mListData == null ? null : mListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        T t = getItem(i);
        if (view == null) {
            mDataBinding = DataBindingUtil.inflate(mInflater, mResLayoutId, viewGroup, false);
        } else {
            mDataBinding = DataBindingUtil.bind(view);
        }

        bindingViews(i, t);

        return mDataBinding.getRoot();
    }


    public abstract void bindingViews(int position, T t);

}
