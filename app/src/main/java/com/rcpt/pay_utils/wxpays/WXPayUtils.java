package com.rcpt.pay_utils.wxpays;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.rcpt.base.HttpResult;
import com.rcpt.base.RcptApl;
import com.rcpt.bean.WxPayBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

public class WXPayUtils {

    private Context mContext;
    private PayReq req;
    private final IWXAPI msgApi;

    public WXPayUtils(Context context) {
        mContext = context;
        req = new PayReq();
        msgApi = RcptApl.getInstance().getWxApi();
    }

    public void openWxPay(String user_id, String course_name, String body, String course_id, String total_amount, String commodity_id, String subject_id, String teaching_style, String subject, String orderNum) {
        boolean isPaySupported = msgApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (isPaySupported) {
            RetrofitManager.toSubscribe(
                    ApiClient.getApiService().getWeixinOrderInfo(user_id, course_name, body, course_id, total_amount, "127.0.0.1", commodity_id, subject_id, teaching_style, subject, orderNum)
                    , new ProgressSubscriber<HttpResult<WxPayBean>>(mContext, new RequestImpl<HttpResult<WxPayBean>>() {
                        @Override
                        public void onNext(HttpResult<WxPayBean> result) {
                            System.out.println(result.getData());
                            genPayReq(result.getData());

                        }
                    })
            );
        } else {
            Toast.makeText(mContext, "您的手机暂不支持微信支付，请检测您是否已安装了微信客户端", Toast.LENGTH_LONG).show();
        }

    }

    private void genPayReq(WxPayBean bean) {
        WxPayBean.CallAppParamBean callAppParam = bean.getCallAppParam();
        req.appId = callAppParam.getAppid();
        req.partnerId = callAppParam.getPartnerid();
        req.prepayId = callAppParam.getPrepayid();
        req.packageValue = callAppParam.getPackageX();
        req.nonceStr = callAppParam.getNoncestr();
        req.timeStamp = callAppParam.getTimestamp();

        req.sign = callAppParam.getSign();
        Log.i(TAG, "genPayReq: " + req.packageValue);
        sendPayReq(callAppParam.getAppid());
    }

    private static final String TAG = "WXPayUtils";
    private void sendPayReq(String appId) {
        msgApi.registerApp(appId);
        msgApi.sendReq(req);
    }

}
