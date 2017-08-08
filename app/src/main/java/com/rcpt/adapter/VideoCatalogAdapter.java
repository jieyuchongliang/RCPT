package com.rcpt.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.R;
import com.rcpt.base.adapter.BaseFoldAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.databinding.ItemLayoutVideoCatalogListContentBinding;
import com.rcpt.databinding.ItemLayoutVideoCatalogListTitleBinding;

import java.util.List;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public class VideoCatalogAdapter extends BaseFoldAdapter<VideoCatalogAdapter.TitleViewHolder, VideoCatalogAdapter.ContentViewHolder> {

    private List<VideoInfoCatalogBean> mListData;
    private boolean isBuy;

    public void setListData(List<VideoInfoCatalogBean> listData) {
        mListData = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
        notifyDataSetChanged();
    }

    @Override
    public int getChildItemCount(int groupPosition) {
        return mListData.get(groupPosition).getLectures() == null ? 0 : mListData.get(groupPosition).getLectures().size();
    }

    @Override
    protected TitleViewHolder getGroupViewHolder(ViewGroup parent) {
        View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_video_catalog_list_title, parent, false);
        ItemLayoutVideoCatalogListTitleBinding bind = DataBindingUtil.bind(titleView);
        return new TitleViewHolder(bind);
    }

    @Override
    protected ContentViewHolder getChildViewHolder(ViewGroup parent) {
        View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_video_catalog_list_content, parent, false);
        ItemLayoutVideoCatalogListContentBinding bind = DataBindingUtil.bind(titleView);
        return new ContentViewHolder(bind);
    }

    @Override
    protected void onBindGroupViewHolder(TitleViewHolder holder, int position) {
        holder.getBinding().setTitleBean(mListData.get(position));
        holder.getBinding().setIsExpand(isGroupExpand(position));
    }

    @Override
    protected void onBindChildViewHolder(ContentViewHolder holder, int titlePosition, int contentPosition) {
        holder.getBinding().setContentBean(mListData.get(titlePosition).getLectures().get(contentPosition));
        holder.getBinding().setIsBuy(isBuy);
    }

    public static class TitleViewHolder extends BindingViewHolder<ItemLayoutVideoCatalogListTitleBinding> {

        public TitleViewHolder(ItemLayoutVideoCatalogListTitleBinding binding) {
            super(binding);
        }
    }

    public static class ContentViewHolder extends BindingViewHolder<ItemLayoutVideoCatalogListContentBinding> {

        public ContentViewHolder(ItemLayoutVideoCatalogListContentBinding binding) {
            super(binding);
        }
    }

}
