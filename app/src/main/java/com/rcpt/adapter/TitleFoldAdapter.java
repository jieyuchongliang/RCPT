package com.rcpt.adapter;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.R;
import com.rcpt.base.adapter.BaseFoldAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.JobCategoryBean;
import com.rcpt.databinding.ItemLayoutJobCategoryContentBinding;
import com.rcpt.databinding.ItemLayoutJobCategoryTitleBinding;

import java.util.List;

/**
 * Created by lvzp on 2017/3/24.
 * 类描述：
 */

public class TitleFoldAdapter extends BaseFoldAdapter<TitleFoldAdapter.TitleViewHolder, TitleFoldAdapter.ContentViewHolder> {

    private List<JobCategoryBean> mListData;
    private String mSelectItem;
    private JobCategoryBean.ChildBean mSelectBean;

    public void bindListData(List<JobCategoryBean> list) {
        mListData = list;
        notifyDataSetChanged();
    }

    public JobCategoryBean.ChildBean getSelectBean() {
        return mSelectBean;
    }

    public void setSelectBean(JobCategoryBean.ChildBean mSelectBean) {
        this.mSelectBean = mSelectBean;
    }

    public void setupSelectItem(String selectItem) {
        mSelectItem = selectItem;
    }

    public String getSelectItem() {
        return mSelectItem;
    }

    @Override
    public int getGroupItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public int getChildItemCount(int groupPosition) {
        return mListData.get(groupPosition).getChild() == null ? 0 : mListData.get(groupPosition).getChild().size();
    }

    public JobCategoryBean getGroupItem(int groupPos) {
        return mListData.get(groupPos);
    }

    public JobCategoryBean.ChildBean getChildItem(int groupPos, int childPos) {
        return mListData.get(groupPos).getChild().get(childPos);
    }

    @Override
    protected TitleViewHolder getGroupViewHolder(ViewGroup parent) {
        View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_job_category_title, parent, false);
        ItemLayoutJobCategoryTitleBinding binding = DataBindingUtil.bind(titleView);
        return new TitleViewHolder(binding);
    }

    @Override
    protected ContentViewHolder getChildViewHolder(ViewGroup parent) {
        View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_job_category_content, parent, false);
        ItemLayoutJobCategoryContentBinding binding = DataBindingUtil.bind(titleView);
        return new ContentViewHolder(binding);
    }

    @Override
    protected void onBindGroupViewHolder(TitleViewHolder holder, int position) {
        holder.getBinding().setTitleText(getGroupItem(position).getName());
    }

    @Override
    protected void onBindChildViewHolder(ContentViewHolder holder, int titlePosition, int contentPosition) {
        JobCategoryBean.ChildBean childItem = getChildItem(titlePosition, contentPosition);
        if (!TextUtils.isEmpty(mSelectItem) && childItem.getName().equals(mSelectItem)||childItem.isSelected()) {
            childItem.setSelected(true);
            mSelectBean = childItem;
        }
        holder.getBinding().setContentBean(childItem);
    }


    class TitleViewHolder extends BindingViewHolder<ItemLayoutJobCategoryTitleBinding> {

        TitleViewHolder(ItemLayoutJobCategoryTitleBinding binding) {
            super(binding);
        }
    }

    class ContentViewHolder extends BindingViewHolder<ItemLayoutJobCategoryContentBinding> {

        ContentViewHolder(ItemLayoutJobCategoryContentBinding binding) {
            super(binding);
        }
    }

}
