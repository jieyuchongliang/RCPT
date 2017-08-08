package com.rcpt.mvp.presenter;

import android.view.View;
import android.widget.CheckBox;

import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.mvp.contract.ListFilterContract;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public abstract class ListFilterPresenter<V extends ListFilterContract.View> extends BasePresenter<V> implements ListFilterContract.Presenter {

    protected CheckBox mOldSelectFilterItem;

    /**
     * Item的点击事件
     */
    @Override
    public void onClick(View v) {
        if (v instanceof CheckBox) {
            if (mOldSelectFilterItem != null && mOldSelectFilterItem != v) {
                mOldSelectFilterItem.setChecked(false);
            }
            mOldSelectFilterItem = (CheckBox) v;
            boolean checked = mOldSelectFilterItem.isChecked();
            if (checked) {
                //如果需要单独处理事件，则进行返会true，就不会继续向下执行
                if (v.getId() == R.id.cbx_app_title_layout_right) {
                    cleanAllFlag();
                    getView().resetFilterAllName();
                    loadListDataWithClean();
                    getView().hintTitleRightView();
                    getView().layoutStartOut();
                    getView().closeGrayBg();
                    return;
                }
                boolean b = onFilterItemCheckClick(v);
                if (b)
                    return;
                getView().layoutStartIn();
                getView().showGrayBg();
            } else {
                getView().layoutStartOut();
                getView().closeGrayBg();
            }
        }
    }

    /**
     * 返回选择的数据对象
     *
     * @param bean
     */
    @Override
    public void onAutoCallback(FilterSelectBean bean) {
        String id = bean.getId();
        String value = bean.getValue();
        onFilterListItemClick(mOldSelectFilterItem, id);
        mOldSelectFilterItem.setText(value);
        mOldSelectFilterItem.setChecked(false);
        loadListDataWithClean();
        getView().layoutStartOut();
        getView().closeGrayBg();
        getView().showTitleRightView();
    }

    /**
     * 当条目再次选中后的回调
     *
     * @param bean
     * @param position
     */
    @Override
    public void onReselectedCallback(FilterSelectBean bean, int position) {
        getView().layoutStartOut();
        getView().closeGrayBg();
    }

    /**
     * 当筛选类型的View处于选中状态时的回调
     *
     * @param view 当前处于选中状态的筛选类型
     * @return 是否要跳过后面方法的执行, true为跳过，false不跳过
     */
    protected abstract boolean onFilterItemCheckClick(View view);

    /**
     * 清空列表重新加载数据
     */
    protected abstract void loadListDataWithClean();

    /**
     * 当筛选列表的Item被点击时进行时进行的回调
     *
     * @param filterItem 筛选的类型View
     * @param id         选择列表的属性id
     */
    protected abstract void onFilterListItemClick(View filterItem, String id);

    /**
     * 清除所有选中的筛选标志
     */
    protected abstract void cleanAllFlag();

}
