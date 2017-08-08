package com.rcpt.adapter;

import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.MenuBean;
import com.rcpt.databinding.ItemLayoutMeMenuBinding;

/**
 * Created by lvzp on 2017/3/1.
 * 类描述：
 */
public class MeMenuAdapter extends BindingBaseRecycleAdapter<MenuBean, ItemLayoutMeMenuBinding> {

    public MeMenuAdapter(int resLayoutId) {
        super(resLayoutId);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutMeMenuBinding> holder, int position, MenuBean menuBean) {
        ItemLayoutMeMenuBinding binding = holder.getBinding();
        binding.setMenuBean(menuBean);
        binding.ivMenuIcon.setImageDrawable(menuBean.getMenuIcon());

    }
}
