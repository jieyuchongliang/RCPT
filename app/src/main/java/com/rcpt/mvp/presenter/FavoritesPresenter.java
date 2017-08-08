package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FavoritesBean;
import com.rcpt.mvp.contract.FavoritesContract;
import com.rcpt.mvp.module.FavoritesModule;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/7.
 * 类描述：
 */

public class FavoritesPresenter extends BasePresenter<FavoritesContract.View> implements FavoritesContract.Presenter {

    private FavoritesModule mModule;
    private String userId;

    @Override
    public void attach(FavoritesContract.View view) {
        super.attach(view);
        mModule = new FavoritesModule();
        userId = LoginHelper.getInstance().getUserToken();

    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
        loadListData(1);
    }

    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page) {
        mModule.getList(getView().getContext(), userId, page, new OnDataGetCallback<List<FavoritesBean.FavoritesListBean>>() {


            @Override
            public void onSuccessResult(List<FavoritesBean.FavoritesListBean> favoritesListBeen) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(favoritesListBeen);
            }
        });
    }

    @Override
    public void onItemLongClick() {
        int position = getView().getClickPosition();
        if (position > mModule.getListData().size() || position < 0)
            return;
        FavoritesBean.FavoritesListBean bean = mModule.getListData().get(position);
        mModule.deleteFavorite(getView().getContext(), LoginHelper.getInstance().getUserToken(), bean.getRecordId(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                mModule.getListData().remove(getView().getClickPosition());
                getView().deleteListItemUpdate(getView().getClickPosition());
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        FavoritesBean.FavoritesListBean bean = mModule.getListData().get(position);
        if (0 == bean.getDelFlg()) {
            RecruitJobInfoActivity.actionStart(getView().getContext(), bean.getCompanyId(), bean.getRecruitmentInfo());
        } else {
            getView().showToast("该职位已删除");
        }

    }
}
