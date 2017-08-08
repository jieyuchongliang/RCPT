package com.rcpt.mvp.contract;

import android.content.Context;
import android.support.annotation.StringDef;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.HomeInfoBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public interface HomeInfoContract {

    String INFO_TYPE_COMPANY_NEWS = "company";//企业新闻
    String INFO_TYPE_NEWS = "news";//新闻详情
    String INFO_TYPE_CONSULT_SERVICE = "consult_service";//咨询服务详情
    String INFO_TYPE_POLICY = "policy";//政策法规详情个
    String INFO_TYPE_EMPLOYMENT = "employment";//求职指南（关于就业的类型）

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({INFO_TYPE_COMPANY_NEWS, INFO_TYPE_CONSULT_SERVICE, INFO_TYPE_NEWS, INFO_TYPE_POLICY, INFO_TYPE_EMPLOYMENT})
    @interface HomeInfoType {
    }

    interface View extends BaseView {
        void updateInfo(HomeInfoBean bean);

        String getId();
    }

    interface Presenter {
        void loadInfo(String type);

        //拨打电话
        void call(String phoneNumber);

        //申请拨打电话的权限
        void applyPermission(Context context);
    }

}
