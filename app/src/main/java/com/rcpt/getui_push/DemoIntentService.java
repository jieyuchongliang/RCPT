package com.rcpt.getui_push;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushActivity;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.rcpt.bean.NotifyInfoBean;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.utils.NewMessageNotification;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.e(TAG, "onReceiveClientId -> " + "pid = " + pid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String jsonData = new String(msg.getPayload());
        //自定义处理透传消息
        Log.e(TAG, "onReceiveClientId -> " + "msg = " + jsonData);
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        Gson gson = new Gson();
        NotifyInfoBean bean = gson.fromJson(jsonData, NotifyInfoBean.class);
        SystemMessageBean.MessageListBean messageBean = new SystemMessageBean.MessageListBean();
        messageBean.setTitle(bean.getTitle());
        messageBean.setMessage(bean.getContent());
        messageBean.setTime(bean.getCreate_time());
        NewMessageNotification.notify(getBaseContext(), messageBean);
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + online);
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG, "onReceiveClientId -> " + "getAction = " + cmdMessage.getAction());
    }


}
