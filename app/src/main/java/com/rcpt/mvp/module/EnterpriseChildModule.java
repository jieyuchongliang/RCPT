package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.EnterpriseListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public class EnterpriseChildModule {

    private List<EnterpriseListBean.BusinesslistBean> mEnterpriseList;
    private boolean isEnd;

    public void requestEnterpriseList(String id,String keyword, int pageNum, Context context, final OnDataGetCallback<List<EnterpriseListBean.BusinesslistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getEnterpriseList(id,keyword, pageNum)
                , new ProgressSubscriber<HttpResult<EnterpriseListBean>>(context, new RequestImpl<HttpResult<EnterpriseListBean>>() {
                    @Override
                    public void onNext(HttpResult<EnterpriseListBean> result) {
                        EnterpriseListBean data = result.getData();
                        isEnd = !data.isIsNext();
                        if (mEnterpriseList == null) {
                            mEnterpriseList = data.getBusinesslist();
                        } else {
                            mEnterpriseList.addAll(data.getBusinesslist());
                        }
                        callback.onSuccessResult(mEnterpriseList);
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<EnterpriseListBean.BusinesslistBean> getEnterpriseList() {
        return mEnterpriseList;
    }
}
