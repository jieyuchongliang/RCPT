package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstituteBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/13.
 */

public class InstitutionModule {

    private List<InstituteBean.InstitutionListBean> mListData;
    private boolean isEnd;

    public void listData(final Context context, int page, String industry, String institutionName, final OnDataGetCallback<List<InstituteBean.InstitutionListBean>> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getInstituteList(page),
                new ProgressSubscriber<>(context, new RequestImpl<HttpResult<InstituteBean>>() {

            @Override
            public void onNext(HttpResult<InstituteBean> result) {
                InstituteBean bean = result.getData();
                if(mListData == null){
                    mListData = bean.getInstitutionList();
                }else{
                    mListData.addAll(bean.getInstitutionList());
                }
                isEnd = !bean.isIsNext();
                callback.onSuccessResult(mListData);
            }
        }));
    }

    public List<InstituteBean.InstitutionListBean> getListData(){
        return mListData;
    }

    public boolean IsEnd() {
        return isEnd;
    }
}
