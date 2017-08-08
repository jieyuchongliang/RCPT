package com.rcpt.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.ui.pay.PayStatusActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    private TextView mTvStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        mTvStatus = (TextView) findViewById(R.id.tv_status);
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResp(BaseResp resp) {
        //Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        mTvStatus.setText(" errCode = " + resp.errCode + " --- resp.errStr -----" + resp.errStr);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(WXPayEntryActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(WXPayEntryActivity.this, "您已取消该订单", Toast.LENGTH_SHORT).show();
                    break;
            }
            PayStatusActivity.actionStart(this, resp.errCode == 0, false);
            finish();
        }
    }
}