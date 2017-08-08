package com.rcpt.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.igexin.sdk.PushActivity;
import com.igexin.sdk.PushManager;
import com.lecloud.sdk.config.LeCloudPlayerConfig;
import com.lecloud.sdk.listener.OnInitCmfListener;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.getui_push.DemoIntentService;
import com.rcpt.getui_push.DemoPushService;
import com.rcpt.http.RetrofitManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * Created by lvzp on 2017/3/7.
 * 类描述：
 */

public class RcptApl extends Application {

    private static RcptApl sApl;
    public static boolean cdeInitSuccess;
    private IWXAPI msgApi;

    public IWXAPI getWxApi() {
        return msgApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApl = this;
        try {
            msgApi = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, false);
            // 将该app注册到微信
            msgApi.registerApp(Constant.WX_APP_ID);
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(sApl, DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(sApl, DemoIntentService.class);
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("HttpLogo")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");
        LoginHelper.getInstance().init(this);
        RetrofitManager.getInstance().init(this);
        initLePlay();
    }

    private void initLePlay() {
        String processName = getProcessName(this, android.os.Process.myPid());
        if (getApplicationInfo().packageName.equals(processName)) {
            SharedPreferences preferences = getSharedPreferences("host", Context.MODE_PRIVATE);
            int host = preferences.getInt("host", LeCloudPlayerConfig.HOST_DEFAULT);
            try {
                //设置日志开关
                LeCloudPlayerConfig.setLogOutputType(LeCloudPlayerConfig.LOG_LOGCAT);
                LeCloudPlayerConfig.setHostType(host);
//                LeCloudPlayerConfig.USE_CDE_PORT = true;
                LeCloudPlayerConfig.setmInitCmfListener(new OnInitCmfListener() {

                    @Override
                    public void onCdeStartSuccess() {
                        //cde启动成功,可以开始播放
                        cdeInitSuccess = true;
                        Log.d("huahua", "onCdeStartSuccess: ");
                    }

                    @Override
                    public void onCdeStartFail() {
                        //cde启动失败,不能正常播放;如果使用remote版本则可能是remote下载失败;
                        //如果使用普通版本,则可能是so文件加载失败导致
                        cdeInitSuccess = false;
                        Log.d("huahua", "onCdeStartFail: ");
                    }

                    @Override
                    public void onCmfCoreInitSuccess() {
                        //不包含cde的播放框架需要处理
                    }

                    @Override
                    public void onCmfCoreInitFail() {
                        //不包含cde的播放框架需要处理
                    }

                    @Override
                    public void onCmfDisconnected() {
                        //cde服务断开,会导致播放失败,重启一次服务
                        try {
                            cdeInitSuccess = false;
                            LeCloudPlayerConfig.init(getApplicationContext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                LeCloudPlayerConfig.init(getApplicationContext());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static RcptApl getInstance() {
        return sApl;
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
