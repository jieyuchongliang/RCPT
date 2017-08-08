package com.rcpt.base.adapter;

import android.databinding.ViewDataBinding;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：
 */

public class SimpleBindingAdapter<T> extends BindingBaseRecycleAdapter<T, ViewDataBinding> {

    private int mVariable;

    public SimpleBindingAdapter(int resLayoutId, int variable) {
        super(resLayoutId);
        mVariable = variable;
    }

    @Override
    protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, int position, T t) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(mVariable, t);
        binding.executePendingBindings();
    }
}
