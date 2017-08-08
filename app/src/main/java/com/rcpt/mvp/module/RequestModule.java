package com.rcpt.mvp.module;

import com.rcpt.base.HttpResult;
import com.rcpt.base.bean.CodeBean;
import com.rcpt.bean.NewsListBean;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.bean.User;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：专门用于请求数据的Module类
 */

public class RequestModule {

    public void startRegisteEmail(String account, String password, String email, String verify, Subscriber<HttpResult<String>> subscriber) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().personReg("mail", account, password, email, null, verify)
                , subscriber);
    }

    public void startSendEmailVerify(String email, Subscriber<HttpResult<CodeBean>> subscriber){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().personSendEmailCode(email)
                , subscriber
        );
    }

    public void startLogin(String account, String password, Subscriber<HttpResult<User>> subscriber) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().login(account, password)
                , subscriber
        );
    }

    public void policyType(Subscriber<HttpResult<List<PolicyTypeBean>>> subscriber) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().policyType()
                , subscriber
        );
    }


    public void newsType(Subscriber<HttpResult<List<PolicyTypeBean>>> subscriber) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().newsType()
                , subscriber
        );
    }

    public void newsList(String id, Subscriber<HttpResult<List<NewsListBean>>> subscriber) {

    }


    /**
     * 根据手机号码重置密码-获取短信验证码
     *
     * @param phoneNum      手机号码
     * @param subscriber
     */
    public void startSendCodeByPhone(String phoneNum, Subscriber<HttpResult<CodeBean>> subscriber){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().sendCodeByPhone(phoneNum)
                , subscriber
        );
    }

    /**
     * 根据手机号码重置密码-修改密码
     * @param phoneNum      手机号码
     * @param pwd           新密码
     * @param subscriber
     */
    public void startEditCodeByPhone(String phoneNum, String pwd, Subscriber<HttpResult<String>> subscriber){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().editCodeByPhone(phoneNum,pwd)
                , subscriber
        );
    }

    /**
     * 根据邮箱重置密码-获取邮箱验证码
     *
     * @param email         邮箱号
     * @param subscriber
     */
    public void startSendCodeByEmail(String email, Subscriber<HttpResult<CodeBean>> subscriber){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().sendCodeByEmail(email)
                , subscriber
        );
    }

    /**
     * 根据邮箱重置密码-修改密码
     * @param email         邮箱号
     * @param pwd           新密码
     * @param subscriber
     */
    public void startEditCodeByEmail(String email, String pwd, Subscriber<HttpResult<String>> subscriber){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().editCodeByEmail(email,pwd)
                , subscriber
        );
    }

    public void saveCID(String username,String cid){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().saveCID(username, cid)
                , new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {

                    }
                }
        );
    }
}
