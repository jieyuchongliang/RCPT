package com.rcpt.adapter;

import android.view.View;

import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.SendRecordBean;
import com.rcpt.databinding.ItemLayoutSendRecordBinding;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public class SendRecordAdapter extends BindingBaseRecycleAdapter<SendRecordBean.DeliveryRecordListBean, ItemLayoutSendRecordBinding> {

    private OnItemDeleteClickListener mOnDeleteClickListener;

    public SendRecordAdapter() {
        super(R.layout.item_layout_send_record);
    }

    public void setOnDeleteClickListener(OnItemDeleteClickListener listener) {
        mOnDeleteClickListener = listener;
    }

    @Override
    protected void bindingViews(final BindingViewHolder<ItemLayoutSendRecordBinding> holder, int position, SendRecordBean.DeliveryRecordListBean deliveryRecordListBean) {
        holder.getBinding().setRecordBean(deliveryRecordListBean);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDeleteClickListener != null) {
                    mOnDeleteClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            }
        });
    }

    public interface OnItemDeleteClickListener {
        void onItemClick(View view, int position);
    }

}
