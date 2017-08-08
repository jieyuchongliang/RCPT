package com.rcpt.widget;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.databinding.ItemLayoutSimpleTextBinding;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * Created by lvzp on 2017/3/24.
 * 类描述：
 */

public class SimpleListSelectPopWindow extends ListSelectPopWindow<String> {

    private SimpleBindingAdapter<String> mAdapter;

    /**
     * 初始化一个PopupWindow
     *
     * @param context 上下文对象
     */
    public SimpleListSelectPopWindow(Activity context) {
        super(context);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<String>(R.layout.item_layout_simple_text, BR.value) {
            @Override
            protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, int position, String s) {
                super.bindingViews(holder, position, s);
                ItemLayoutSimpleTextBinding binding = (ItemLayoutSimpleTextBinding) holder.getBinding();
                binding.setIsSelect(s.equals(mSelectItemName));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if (mCallback != null) {
                    int position = vh.getLayoutPosition();
                    mCallback.onSelectCallback(mAdapter.getItem(position), position);
                }
                dismiss();
            }
        });
    }

    @Override
    public void bindListData(List<String> listData) {
        mAdapter.setupData(listData);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mAdapter.cleanData();
    }
}
