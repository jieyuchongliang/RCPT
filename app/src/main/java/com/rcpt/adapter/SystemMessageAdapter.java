package com.rcpt.adapter;

import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.databinding.ItemLayoutSystemMessageBinding;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class SystemMessageAdapter extends BindingBaseRecycleAdapter<SystemMessageBean.MessageListBean, ItemLayoutSystemMessageBinding> {

    public SystemMessageAdapter(int resLayoutId) {
        super(resLayoutId);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutSystemMessageBinding> holder, int position, SystemMessageBean.MessageListBean systemMessageBean) {
        holder.getBinding().setBean(systemMessageBean);
    }
}
