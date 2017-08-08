package com.rcpt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.rcpt.bean.User;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.ui.system.SystemMainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(2500);
        rootLayout.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
                Intent mainIntent = new Intent();
                //如果没有登录，就直接进入到MainActivity
                if (!LoginHelper.getInstance().isOnline()) {
                    mainIntent.setClass(SplashActivity.this, MainActivity.class);
                } else {
                    //登录后判断是否为管理员用户，如果为管理员用户，就直接进入到管理员管理页面
                    User userBean = LoginHelper.getInstance().getUserBean();
                    if (Constant.UserType.MANAGE.getValue().equals(userBean.getUserType())) {
                        mainIntent.setClass(SplashActivity.this, SystemMainActivity.class);
                    } else {
                        mainIntent.setClass(SplashActivity.this, MainActivity.class);
                    }
                }
                startActivity(mainIntent);
                finish();
            }
        }, 3600); //2900 for release

    }

    @Override
    public void onBackPressed() {
    }
}
