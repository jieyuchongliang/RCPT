package com.rcpt.mvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.HomeInfoBean;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.module.HomeInfoModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;
import com.rcpt.utils.DialogUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class HomeInfoPresenter extends BasePresenter<HomeInfoContract.View> implements HomeInfoContract.Presenter {

    private HomeInfoModule mModule;
    @Override
    public void attach(HomeInfoContract.View view) {
        super.attach(view);
        mModule = new HomeInfoModule();
    }

    @Override
    public void loadInfo(String type) {
        mModule.requestHomeInfo(getView().getContext(),getView().getId(), type, new OnDataGetCallback<HomeInfoBean>() {
            @Override
            public void onSuccessResult(HomeInfoBean bean) {
                getView().updateInfo(bean);
            }
        });
    }

    @Override
    public void call(final String phoneNumber) {
        String permission = "android.permission.CALL_PHONE"; //你要判断的权限名字
        int res = getView().getContext().checkCallingOrSelfPermission(permission);
        boolean isPermit = (res == PackageManager.PERMISSION_GRANTED);
        //判断用户是否通过了拨打电话的权限
        if (isPermit) {
            DialogUtils.builderAlertDialog(getView().getContext(),null,phoneNumber)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:"+phoneNumber));
                            getView().getContext().startActivity(intent);
                        }
                    }).show();
        }else {
            applyPermission(getView().getContext());
        }
    }

    @Override
    public void applyPermission(final Context context) {
        RxPermissions rxPermissions = new RxPermissions((Activity) context);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isAgree) {
                        if (isAgree) {
                            Log.d("permission", "---- 请求通过----");
                        } else {
                            Toast.makeText(context, "权限被拒绝", Toast.LENGTH_SHORT).show();
                            Log.d("permission", "---- 权限被拒绝----" + Manifest.permission.CALL_PHONE);
                        }
                    }
                });
    }

}
