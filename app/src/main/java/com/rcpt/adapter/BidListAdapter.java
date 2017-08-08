package com.rcpt.adapter;

import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.BidListBean;
import com.rcpt.databinding.ItemLayoutBidListBinding;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidListAdapter extends BindingBaseRecycleAdapter<BidListBean.ProjectlistBean, ItemLayoutBidListBinding> {


    public BidListAdapter() {
        super(R.layout.item_layout_bid_list);
    }

    @Override
    public void bindingViews(BindingViewHolder<ItemLayoutBidListBinding> holder, int position, BidListBean.ProjectlistBean bidListBean) {
        holder.getBinding().setBidListBean(bidListBean);
    }
}
