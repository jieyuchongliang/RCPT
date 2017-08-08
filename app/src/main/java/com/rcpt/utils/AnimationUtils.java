package com.rcpt.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by lvzp on 2017/4/12.
 * 类描述：
 */

public class AnimationUtils {
    /**
     * View在Y轴的滑动动画
     *
     * @param view
     * @param height
     * @param isIn
     * @return
     */
    public static ObjectAnimator mackTranslationYAnimation(View view, int height, boolean isIn) {
        ObjectAnimator a;
        if (isIn)
            //打开动画
            a = ObjectAnimator.ofFloat(
                    view, "translationY", height);
        else
            //关闭动画
            a = ObjectAnimator.ofFloat(
                    view, "translationY", -height);
        a.setDuration(600);
        //a.setInterpolator(new AccelerateInterpolator());
        return a;
    }

    /**
     * View淡入淡出的动画
     *
     * @param view
     * @param isShow
     * @return
     */
    public static Animation mackAlphaAnimation(View view, boolean isShow) {
        Animation a;
        if (isShow) {
            a = new AlphaAnimation(0.f, 0.5f);
        } else {
            a = new AlphaAnimation(0.5f, 0.f);
        }
        a.setDuration(100);
        view.startAnimation(a);
        return a;
    }

    /**
     * View的角度旋转
     *
     * @param view
     * @param from
     * @param to
     */
    public static void rotationExpandIcon(final View view, float from, float to) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(150);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setRotation((Float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

}
