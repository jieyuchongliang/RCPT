package com.rcpt.widget;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.rcpt.base.adapter.BaseFoldAdapter;
import com.rcpt.adapter.TitleFoldAdapter;
import com.rcpt.bean.JobCategoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/24.
 * 类描述：带有层级关系的列表选择的Pop
 */

public class TitleListSelectPopWindow extends ListSelectPopWindow<JobCategoryBean> {

    public static final int SELECT_MODE_SINGLE = 0x00000001;//单选模式
    public static final int SELECT_MODE_MORE = 0x00000005;//多选模式
    public static final int SELECT_MODE_INFINITE = 0x00000010;//无限制

    private TitleFoldAdapter mAdapter;
    private List<JobCategoryBean.ChildBean> mSelectedJobCategoryList;

    private int mSelectMode;
    private int mMaxSelected;
    private boolean isFirst = true;

    /**
     * 初始化一个PopupWindow
     *
     * @param context 上下文对象
     */
    public TitleListSelectPopWindow(final Activity context) {
        super(context);
        mAdapter = new TitleFoldAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mAdapter);
        mRightView.setVisibility(View.VISIBLE);
        mRightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickSaveCallback != null) {
                    if (mSelectMode == SELECT_MODE_SINGLE){
                        if (mAdapter.getSelectBean() == null) {
                            Toast.makeText(context, "请至少选择一个职位类别", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        addSelectItemToList();
                    }
                   
                    mClickSaveCallback.onSaveCallback(null);
                    dismiss();
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseFoldAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int groupPos, int childPos, boolean isGroup) {
                if (!isGroup) {
                    if (isFirst) {
                        if (!TextUtils.isEmpty(mAdapter.getSelectItem())) {
                            mAdapter.setupSelectItem("");
                        }
                        addSelectItemToList();
                        mAdapter.setSelectBean(null);
                        isFirst = false;
                    }

                    JobCategoryBean.ChildBean childBean = mAdapter.getChildItem(groupPos, childPos);

                    switch (mSelectMode) {
                        case SELECT_MODE_SINGLE:
                        case SELECT_MODE_MORE:
                        case SELECT_MODE_INFINITE:
                            if (childBean.isSelected()) {
                                mSelectedJobCategoryList.remove(childBean);
                                childBean.setSelected(false);
                            } else {
                                if (mSelectMode == SELECT_MODE_SINGLE) {
                                    if (mSelectedJobCategoryList.size() >= 1) {
                                        Toast.makeText(context, "只可选择一种类型", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } else if (mSelectMode == SELECT_MODE_MORE) {
                                    if (mSelectedJobCategoryList.size() >= mMaxSelected) {
                                        Toast.makeText(context, "最大不可超过" + mMaxSelected + "条数据", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                                childBean.setSelected(true);
                                mSelectedJobCategoryList.add(childBean);
                            }
                            mAdapter.notifyDataChangeChildItem(groupPos, childPos);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void showPopupWindow(View parent) {
        super.showPopupWindow(parent);
        isFirst = true;
    }

    @Override
    public void bindListData(List<JobCategoryBean> list) {
        mAdapter.setupSelectItem(mSelectItemName);
        mAdapter.bindListData(list);
    }

    private void addSelectItemToList() {
        if (mAdapter.getSelectBean() != null && !mSelectedJobCategoryList.contains(mAdapter.getSelectBean())) {
            mSelectedJobCategoryList.add(mAdapter.getSelectBean());
        }
    }

    public void setSelectMode(int selectMode, int maxSelected) {
        mSelectMode = selectMode;
        mMaxSelected = maxSelected;
        mSelectedJobCategoryList = new ArrayList<>();
    }

    public List<JobCategoryBean.ChildBean> getSelectedJobCategoryList() {
        return mSelectedJobCategoryList;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        mRecyclerView.scrollToPosition(0);
        for (JobCategoryBean.ChildBean childBean : mSelectedJobCategoryList) {
            childBean.setSelected(false);
        }
        mSelectedJobCategoryList.clear();
    }

}
