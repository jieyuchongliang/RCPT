package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.mvp.contract.ChooseIndustryContract;
import com.rcpt.mvp.module.ChooseIndustryModule;
import com.rcpt.ui.register.group.ChooseIndustryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860116021 on 2017/3/15.
 */

public class ChooseIndustryPresenter extends BasePresenter<ChooseIndustryContract.View> implements ChooseIndustryContract.Presenter {

    private ChooseIndustryModule mModule;
    private List<ChooseIndustryBean> mSelectBeans;

    @Override
    public void attach(ChooseIndustryContract.View view) {
        super.attach(view);//绑定xml
        mSelectBeans = new ArrayList<>();
        mModule = new ChooseIndustryModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {

        mModule.requestChooseIndustryList(getView().getContext(), new OnDataGetCallback<List<ChooseIndustryBean>>() {
            @Override
            public void onSuccessResult(List<ChooseIndustryBean> chooseIndustryBeen) {
                //获取上次选择的职位列表
                List<ChooseIndustryBean> selectedList = getView().getSelectedBeanList();
                if (selectedList != null && selectedList.size() > 0) {
                    for (ChooseIndustryBean bean : chooseIndustryBeen) {
                        for (int i = 0; i < selectedList.size(); i++) {
                            ChooseIndustryBean chooseIndustryBean = selectedList.get(i);
                            if (bean.getId().equals(chooseIndustryBean.getId())) {
                                bean.setSelect(true);
                                mSelectBeans.add(bean);
                                selectedList.remove(chooseIndustryBean);
                                break;
                            }
                        }
                    }
                }
                getView().bindListData(chooseIndustryBeen);
            }
        });

    }

    @Override
    public List<ChooseIndustryBean> getSelectBeanList() {
        return mSelectBeans;
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {

        ChooseIndustryBean bean = mModule.getListDate().get(position);
        if (bean.isSelect()) {
            mSelectBeans.remove(bean);
            bean.setSelect(false);
        } else {
            if (getView().getSelectedMode() == ChooseIndustryActivity.SELECT_MODE_MORE) {
                if (mSelectBeans.size() >= 5) {
                    getView().showToast("最大不可超过5条");
                    return;
                }
            }
            mSelectBeans.add(bean);
            bean.setSelect(true);
        }
        getView().onItemSelectUpdate(position);
    }
}
