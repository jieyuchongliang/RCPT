package com.rcpt;

import android.content.Context;
import android.util.Log;

import com.rcpt.bean.User;
import com.rcpt.utils.UserUtils;

import java.lang.ref.WeakReference;


/**
 * 作者：吕振鹏
 * 创建时间：10月21日
 * 时间：15:31
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class LoginHelper {

    private static LoginHelper sInstance;
    private WeakReference<Context> mContext;
    private User mUserBean;
    private boolean isOnline;

    private LoginHelper() {
    }

    public static LoginHelper getInstance() {
        if (sInstance == null) {
            sInstance = new LoginHelper();
        }
        return sInstance;
    }

    public LoginHelper init(Context context) {
        mContext = new WeakReference<>(context);
        isOnline = checkIsOnline();
        return this;
    }

    private boolean checkIsOnline() {
        boolean isOnline;
        User user = UserUtils.getUser(mContext.get());
        if (user.getToken() != null) {
            isOnline = true;
            Log.d(LoginHelper.class.getSimpleName(),"--用户id为" + user.getToken());
        } else {
            System.out.println("--用户对象为空");
            isOnline = false;
        }
        if (isOnline) {
            mUserBean = user;
        }
        return isOnline;
    }


    public boolean userExit() {
        mUserBean = null;
        isOnline = false;
        return UserUtils.quit(mContext.get());
    }

    public User getUserBean() {
        if (mUserBean == null)
            return new User();
        return mUserBean;
    }

    public String getUserToken() {
        if (mUserBean != null)
            return mUserBean.getToken();
        return "";
    }


    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        if (online) {
            mUserBean = UserUtils.getUser(mContext.get());
        } else {
            userExit();
        }
        isOnline = online;
    }


}
