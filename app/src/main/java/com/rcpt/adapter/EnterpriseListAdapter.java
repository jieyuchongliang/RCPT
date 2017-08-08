package com.rcpt.adapter;

import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.EnterpriseListBean;
import com.rcpt.databinding.ItemLayoutEnterpriseListBinding;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class EnterpriseListAdapter extends BindingBaseRecycleAdapter<EnterpriseListBean.BusinesslistBean, ItemLayoutEnterpriseListBinding> {

    public EnterpriseListAdapter(int resLayoutId) {
        super(resLayoutId);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutEnterpriseListBinding> holder, int position, EnterpriseListBean.BusinesslistBean enterpriseListBean) {
        holder.getBinding().setBean(enterpriseListBean);
    }
}
