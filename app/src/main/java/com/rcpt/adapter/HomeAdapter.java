package com.rcpt.adapter;

import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.InstituteBean;
import com.rcpt.databinding.ItemLayoutHomeNewsFollowBinding;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class HomeAdapter extends BindingBaseRecycleAdapter<InstituteBean.InstitutionListBean, ItemLayoutHomeNewsFollowBinding> {


    public HomeAdapter(int resLayoutId) {
        super(resLayoutId);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutHomeNewsFollowBinding> holder, int position, InstituteBean.InstitutionListBean bean) {
        holder.getBinding().setNewsFollowBean(bean);
    }
}
