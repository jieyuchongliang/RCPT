package com.rcpt.adapter;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.databinding.ItemLayoutRecruitFragmentOneTypeBinding;
import com.rcpt.databinding.ItemLayoutRecruitFragmentTitleBinding;
import com.rcpt.databinding.ItemLayoutRecruitFragmentTwoTypeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：
 */

public class RecruitFragmentAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    public static final String ITEM_TYPE_ONE_TAG = "one";
    public static final String ITEM_TYPE_TWO_TAG = "two";

    public static final int ITEM_TYPE_TITLE = 111;
    public static final int ITEM_TYPE_ONE = 112;
    public static final int ITEM_TYPE_TWO = 123;

    private OnItemClickListener mItemClickListener;
    private List<RecruitFragmentListBean.SubBean> mListData;

    public void setupData(List<RecruitFragmentListBean> resData) {
        if (mListData == null)
            mListData = new ArrayList<>();
        else
            mListData.clear();
        for (RecruitFragmentListBean resListBean : resData) {
            RecruitFragmentListBean.SubBean subBean = new RecruitFragmentListBean.SubBean();
            subBean.setTitle(true);
            subBean.setTitle(resListBean.getTitle());
            subBean.setId(resListBean.getId());
            mListData.add(subBean);
            List<RecruitFragmentListBean.SubBean> subBeanList = resListBean.getSubList();
            for (RecruitFragmentListBean.SubBean bean : subBeanList) {
                subBean.setType(bean.getType());
                mListData.add(bean);
            }
        }
    }

    public List<RecruitFragmentListBean.SubBean> getAdapterListData() {
        return mListData;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (lm instanceof GridLayoutManager) {

            final GridLayoutManager gridManager = ((GridLayoutManager) lm);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    switch (itemViewType) {
                        case ITEM_TYPE_TITLE:
                        case ITEM_TYPE_ONE:
                            return 2;
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        RecruitFragmentListBean.SubBean bean = getItemBean(position);
        if (bean.isTitle())
            return ITEM_TYPE_TITLE;
        switch (bean.getType()) {
            case ITEM_TYPE_ONE_TAG:
                return ITEM_TYPE_ONE;
            case ITEM_TYPE_TWO_TAG:
                return ITEM_TYPE_TWO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_TYPE_TITLE:
                View titleView = inflater.inflate(R.layout.item_layout_recruit_fragment_title, parent, false);
                ItemLayoutRecruitFragmentTitleBinding titleBinding = DataBindingUtil.bind(titleView);
                return new TitleHolder(titleBinding);
            case ITEM_TYPE_ONE:
                View oneView = inflater.inflate(R.layout.item_layout_recruit_fragment_one_type, parent, false);
                ItemLayoutRecruitFragmentOneTypeBinding oneBinding = DataBindingUtil.bind(oneView);
                return new OneTypeHolder(oneBinding);
            case ITEM_TYPE_TWO:
                View twoView = inflater.inflate(R.layout.item_layout_recruit_fragment_two_type, parent, false);
                ItemLayoutRecruitFragmentTwoTypeBinding twoBinding = DataBindingUtil.bind(twoView);
                return new TwoTypeHolder(twoBinding);
        }
        return null;
    }

    @TargetApi(16)
    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.subBean, getItemBean(position));
        holder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    private RecruitFragmentListBean.SubBean getItemBean(int position) {
        return mListData.get(position);
    }

    private class TitleHolder extends BindingViewHolder<ItemLayoutRecruitFragmentTitleBinding> {

        TitleHolder(ItemLayoutRecruitFragmentTitleBinding binding) {
            super(binding);
            binding.getRoot().findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getLayoutPosition(), ITEM_TYPE_TITLE);
                    }
                }
            });
        }
    }

    private class OneTypeHolder extends BindingViewHolder<ItemLayoutRecruitFragmentOneTypeBinding> {

        OneTypeHolder(ItemLayoutRecruitFragmentOneTypeBinding binding) {
            super(binding);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getLayoutPosition(), ITEM_TYPE_ONE);
                    }
                }
            });
        }
    }

    private class TwoTypeHolder extends BindingViewHolder<ItemLayoutRecruitFragmentTwoTypeBinding> {

        TwoTypeHolder(ItemLayoutRecruitFragmentTwoTypeBinding binding) {
            super(binding);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getLayoutPosition(), ITEM_TYPE_TWO);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, int itemViewType);
    }

}
