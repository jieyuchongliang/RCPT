package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.mvp.contract.ChooseProvinceContract;
import com.rcpt.mvp.module.ChooseProvinceModule;

import java.util.List;

/**
 * Created by 860116021 on 2017/3/16.
 */

public class ChooseProvincePresenter extends BasePresenter<ChooseProvinceContract.View> implements ChooseProvinceContract.Presenter {

    private ChooseProvinceModule mModule;
    private ChooseProvinceBean provinceBean;
    private ChooseProvinceBean cityBean;
    private ChooseProvinceBean areaBean;
    private boolean isProvince = true;
    private boolean isCity = false;

    @Override
    public void attach(ChooseProvinceContract.View view) {
        super.attach(view);
        getView().initRecyclerView();
        mModule = new ChooseProvinceModule();
        provinceBean = new ChooseProvinceBean();
        cityBean = new ChooseProvinceBean();
    }

    @Override
    public void loadListData() {
        loadListData("");
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        if (isProvince) {
            provinceBean = mModule.getListData().get(position);
            loadListData(mModule.getListData().get(position).getRegionId());
            isProvince = false;
            isCity = true;
        } else if (isCity) {
            cityBean = mModule.getListData().get(position);
            if (getView().isThree()) {
                loadListData(mModule.getListData().get(position).getRegionId());
                isCity = false;
            }else{
                getView().onCitySelectOver(provinceBean,cityBean,areaBean);
            }
        } else {
            areaBean = mModule.getListData().get(position);
            getView().onCitySelectOver(provinceBean,cityBean,areaBean);
        }
    }

    public void loadListData(String provinceId) {
        mModule.requestProvinceList(getView().getContext(), provinceId, new OnDataGetCallback<List<ChooseProvinceBean>>() {
            @Override
            public void onSuccessResult(List<ChooseProvinceBean> chooseProvinceBeen) {
                getView().bindListData(chooseProvinceBeen);
            }
        });
    }
}
