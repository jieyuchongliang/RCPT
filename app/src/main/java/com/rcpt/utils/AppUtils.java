package com.rcpt.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.ui.main.activity.MainActivity;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class AppUtils {
    public static void exitLogin(Context context, final BaseView view) {
        DialogUtils
                .buildAlertDialogWithCancel(context, "温馨提示", "您是否要退出登录")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        LoginHelper.getInstance().userExit();
                        view.closeActivity();
                        view.actionStartActivity(MainActivity.class);
                        view.showToast("退出成功");
                    }
                }).show();
    }

}
