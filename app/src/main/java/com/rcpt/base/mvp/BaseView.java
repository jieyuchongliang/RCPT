package com.rcpt.base.mvp;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by lvzp on 2017/2/16.
 * 类描述：MVP模式的View接口
 */

public interface BaseView {

    Context getContext();

    void actionStartActivity(Class cls);

    void showToast(String text);

    void closeActivity();

    void showProgressDialog(String message);

    void hindProgressDialog();

    @ColorInt
    int getResColor(@ColorRes int color);
}
