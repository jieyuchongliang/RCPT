package com.rcpt.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.base.adapter.BindingViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/23.
 * 类描述：
 */

public abstract class BaseFoldAdapter<GV extends BindingViewHolder, CV extends BindingViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.AdapterDataObserver mObserver = new MyDataObserver();

    private List<FoldListPosition> mListData;

    private int mTotalChildCount;

    private OnItemClickListener mItemClickListener;
    private OnItemGroupExpandChangeListener mItemGroupExpandChangeListener;

    private boolean isNeedExpand;

    public BaseFoldAdapter() {
        registerAdapterDataObserver(mObserver);
        mListData = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemGroupExpandChangeListener(OnItemGroupExpandChangeListener mItemGroupExpandChangeListener) {
        this.mItemGroupExpandChangeListener = mItemGroupExpandChangeListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case FoldListPosition.GROUP:
                return getGroupViewHolder(parent);
            case FoldListPosition.CHILD:
                return getChildViewHolder(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FoldListPosition pos = mListData.get(position);
        int type = pos.type;
        switch (type) {
            case FoldListPosition.GROUP:
                final GV gvHolder = (GV) holder;
                gvHolder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNeedExpand) {
                            int currentPosition = getCurrentGroupPosition(pos);
                            if (pos.isExpand) {
                                setGroupCollapse(pos.groupPos, currentPosition);
                                pos.isExpand = false;
                            } else {
                                setGroupExpand(pos.groupPos, currentPosition);
                                pos.isExpand = true;
                            }
                            if (mItemGroupExpandChangeListener != null)
                                mItemGroupExpandChangeListener.onExpandChange(gvHolder, pos.isExpand, pos.groupPos, currentPosition);
                        }
                        if (mItemClickListener != null)
                            mItemClickListener.onItemClick(gvHolder, pos.groupPos, pos.childPos, true);
                    }
                });
                onBindGroupViewHolder(gvHolder, pos.groupPos);
                break;
            case FoldListPosition.CHILD:
                final CV cvHolder = (CV) holder;
                cvHolder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null)
                            mItemClickListener.onItemClick(cvHolder, pos.groupPos, pos.childPos, false);
                    }
                });
                onBindChildViewHolder(cvHolder, pos.groupPos, pos.childPos);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getGroupItemCount() + mTotalChildCount;
    }

    public void setNeedExpand(boolean isNeedExpand) {
        this.isNeedExpand = isNeedExpand;
    }

    public boolean isNeedExpand() {
        return isNeedExpand;
    }

    public boolean isGroupExpand(int groupPosition) {
        int size = mListData.size();
        for (int i = 0; i < size; i++) {
            FoldListPosition foldListPosition = mListData.get(i);
            if (foldListPosition.type == FoldListPosition.GROUP && foldListPosition.groupPos == groupPosition) {
                return foldListPosition.isExpand;
            }
        }
        return false;
    }

    /**
     * Group设置为展开状态
     * 注意这个方法一定要写在有数据以后
     *
     * @param groupPosition
     */
    public void groupExpand(int groupPosition) {
        if (!isNeedExpand) return;
        int size = mListData.size();
        for (int i = 0; i < size; i++) {
            FoldListPosition foldListPosition = mListData.get(i);
            if (foldListPosition.type == FoldListPosition.GROUP && foldListPosition.groupPos == groupPosition) {
                foldListPosition.isExpand = true;
                setGroupExpand(groupPosition, i);
                notifyItemChanged(i);
                return;
            }
        }

    }

    private void setGroupExpand(int groupPosition, int currentGroupPosition) {
        int childItemCount = getChildItemCount(groupPosition);
        List<FoldListPosition> childPos = new ArrayList<>();
        for (int i = 0; i < childItemCount; i++) {
            childPos.add(FoldListPosition.obtain(FoldListPosition.CHILD, groupPosition, i));
            mTotalChildCount++;
        }
        int insertedIndex = currentGroupPosition + 1;
        mListData.addAll(insertedIndex, childPos);
        notifyItemRangeInserted(insertedIndex, childItemCount);
    }

    /**
     * Group设置为折叠状态
     * 注意这个方法一定要写在有数据以后
     *
     * @param groupPosition
     */
    public void groupCollapse(int groupPosition) {
        if (!isNeedExpand) return;
        int size = mListData.size();
        for (int i = 0; i < size; i++) {
            FoldListPosition foldListPosition = mListData.get(i);
            if (foldListPosition.type == FoldListPosition.GROUP && foldListPosition.groupPos == groupPosition) {
                foldListPosition.isExpand = false;
                setGroupCollapse(groupPosition, i);
                return;
            }
        }

    }

    private void setGroupCollapse(int groupPosition, int currentGroupPosition) {
        int childItemCount = getChildItemCount(groupPosition);
        int removeIndex = currentGroupPosition + 1;
        for (int i = 0; i < childItemCount; i++) {
            mListData.remove(removeIndex);
            mTotalChildCount--;
        }
        notifyItemRangeRemoved(removeIndex, childItemCount);
    }

    public abstract int getGroupItemCount();

    public abstract int getChildItemCount(int groupPosition);

    protected abstract GV getGroupViewHolder(ViewGroup parent);

    protected abstract CV getChildViewHolder(ViewGroup parent);

    protected abstract void onBindGroupViewHolder(GV holder, int position);

    protected abstract void onBindChildViewHolder(CV holder, int titlePosition, int contentPosition);

    private class MyDataObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onChanged() {
            refreshItem();
        }

    }

    /**
     * 更新指定GroupPosition的数据
     *
     * @param groupPos
     */
    public void notifyDataChangeGroupItem(int groupPos) {
        int titlePosition = 0;
        for (int i = 0; i < groupPos; i++) {
            titlePosition++;//加上当前的标题
            titlePosition += getChildItemCount(groupPos);
        }
        notifyItemChanged(titlePosition);
    }

    /**
     * 更新指定GroupPosition的Child
     *
     * @param groupPos
     * @param childPos
     */
    public void notifyDataChangeChildItem(int groupPos, int childPos) {
        int itemPosition = 0;
        for (int i = 0; i <= groupPos; i++) {
            itemPosition++;//加上当前的标题
            if (i < groupPos) {
                for (int j = 0; j < getChildItemCount(i); j++) {
                    itemPosition++;
                }
            } else {
                itemPosition += childPos;
            }
        }
        notifyItemChanged(itemPosition);
    }

    private void refreshItem() {
        mTotalChildCount = 0;
        mListData.clear();
        for (int i = 0; i < getGroupItemCount(); i++) {
            FoldListPosition position = FoldListPosition.obtain(FoldListPosition.GROUP, i, -1);
            int contentCount = getChildItemCount(i);
            mListData.add(position);
            if (!isNeedExpand) {
                mTotalChildCount += contentCount;
                for (int j = 0; j < contentCount; j++) {
                    FoldListPosition contentPos = FoldListPosition.obtain(FoldListPosition.CHILD, i, j);
                    mListData.add(contentPos);
                }
            }
        }
    }

    private int getCurrentGroupPosition(FoldListPosition position) {
        for (int i = 0; i < mListData.size(); i++) {
            if (position.type == FoldListPosition.GROUP && position.equals(mListData.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private int getCurrentChildPosition(FoldListPosition position) {
        for (int i = 0; i < mListData.size(); i++) {
            if (position.type == FoldListPosition.CHILD && position.equals(mListData.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private static class FoldListPosition {

        static final int GROUP = 0x00000010;
        static final int CHILD = 0x00000015;

        int type;

        int groupPos;
        int childPos;

        boolean isExpand;

        static FoldListPosition obtain(int type, int groupPos, int childPos) {
            FoldListPosition elp = new FoldListPosition();
            elp.type = type;
            elp.groupPos = groupPos;
            elp.childPos = childPos;
            elp.isExpand = false;
            return elp;
        }

        static FoldListPosition obtain(int type, int groupPos, int childPos, boolean isExpand) {
            FoldListPosition elp = new FoldListPosition();
            elp.type = type;
            elp.groupPos = groupPos;
            elp.childPos = childPos;
            elp.isExpand = isExpand;
            return elp;
        }


    }


    public interface OnItemClickListener {
        /**
         * 列表Item的点击事件
         *
         * @param holder
         * @param groupPos
         * @param childPos
         * @param isGroup
         */
        void onItemClick(RecyclerView.ViewHolder holder, int groupPos, int childPos, boolean isGroup);
    }

    /**
     * 列表的父目录折叠状态发送改变时的监听器
     */
    public interface OnItemGroupExpandChangeListener {
        /**
         * 状态改变时的回调方法
         *
         * @param holder
         * @param isExpand        折叠状态
         * @param groupPosition   group的位置
         * @param adapterPosition group在Adapter中的位置
         */
        void onExpandChange(RecyclerView.ViewHolder holder, boolean isExpand, int groupPosition, int adapterPosition);
    }

}
