package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.EnterpriseListBean;
import com.rcpt.mvp.contract.EnterpriseChildContract;
import com.rcpt.mvp.module.EnterpriseChildModule;
import com.rcpt.ui.enterprise.EnterpriseInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public class EnterpriseChildPresenter extends BasePresenter<EnterpriseChildContract.View> implements EnterpriseChildContract.Presenter {

    private EnterpriseChildModule mModule;

    @Override
    public void attach(EnterpriseChildContract.View view) {
        super.attach(view);
        mModule = new EnterpriseChildModule();
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

    public void loadListData(int page) {
        mModule.requestEnterpriseList(getView().getEnterpriseId(), getView().getEnterpriseKeyWord(),page, getView().getContext(), new OnDataGetCallback<List<EnterpriseListBean.BusinesslistBean>>() {
            @Override
            public void onSuccessResult(List<EnterpriseListBean.BusinesslistBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        EnterpriseInfoActivity.actionStart(getView().getContext(), mModule.getEnterpriseList().get(position).getCompanyId());
    }
}
