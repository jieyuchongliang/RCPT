package com.rcpt.adapter;

import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.databinding.ItemLayoutPersonInfoMenuBinding;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public class PersonInfoAdapter extends BindingBaseRecycleAdapter<InputMenuBean, ItemLayoutPersonInfoMenuBinding> {

    public PersonInfoAdapter() {
        super(R.layout.item_layout_person_info_menu);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutPersonInfoMenuBinding> holder, int position, InputMenuBean inputMenuBean) {
        holder.getBinding().setMenuBean(inputMenuBean);
    }


    public void updateAvatar(String imageUrl) {
        updateSingleItem(0, imageUrl);
    }

    public boolean updateSingleItem(int position, String value) {
        InputMenuBean bean = getItem(position);
        String menuValue = bean.getMenuValue();
        if (menuValue != null && menuValue.equals(value)) {
            return false;
        }
        bean.setMenuValue(value);
        notifyItemChanged(position);
        return true;
    }

}
