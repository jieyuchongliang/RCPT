package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyFavoritesListBean;
import com.rcpt.mvp.contract.CompanyFavoriteContract;
import com.rcpt.mvp.module.CompanyFavoriteModule;
import com.rcpt.ui.recruit.RecruitCVPreviewInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/4/14.
 * 类描述：
 */

public class CompanyFavoritePresenter extends BasePresenter<CompanyFavoriteContract.View> implements CompanyFavoriteContract.Presenter {

    private CompanyFavoriteModule mModule;

    @Override
    public void attach(CompanyFavoriteContract.View view) {
        super.attach(view);
        mModule = new CompanyFavoriteModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        loadListData(1);
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page) {
        mModule.requestCompanyFavoriteList(getView().getContext(), LoginHelper.getInstance().getUserToken(), page, new OnDataGetCallback<List<CompanyFavoritesListBean.CompanyFavoritesBean>>() {
            @Override
            public void onSuccessResult(List<CompanyFavoritesListBean.CompanyFavoritesBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickPosition();
        CompanyFavoritesListBean.CompanyFavoritesBean companyFavoritesBean = mModule.getCompanyFavoritesList().get(position);
        if (0 == companyFavoritesBean.getDelFlg()){
            RecruitCVPreviewInfoActivity.actionStart(getView().getContext(), companyFavoritesBean.getCvId(), companyFavoritesBean.getCvUserId());
        }else {
            getView().showToast("该简历已被删除。");
        }
    }

    @Override
    public void onItemLongClick() {
        final int position = getView().getClickPosition();
        if (position > mModule.getCompanyFavoritesList().size() || position < 0)
            return;
        final CompanyFavoritesListBean.CompanyFavoritesBean companyFavoritesBean = mModule.getCompanyFavoritesList().get(position);
        mModule.deleteCompanyFavorites(getView().getContext(), LoginHelper.getInstance().getUserToken(), companyFavoritesBean.getRecordId(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                mModule.getCompanyFavoritesList().remove(companyFavoritesBean);
                getView().deleteListItem(position);
            }
        });
    }
}
