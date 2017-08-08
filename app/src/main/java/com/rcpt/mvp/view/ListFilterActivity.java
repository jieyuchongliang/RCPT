package com.rcpt.mvp.view;

import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.mvp.contract.ListFilterContract;
import com.rcpt.utils.AnimationUtils;
import com.rcpt.utils.ViewUtils;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */
@SuppressLint("WrongConstant")
public abstract class ListFilterActivity<D extends ViewDataBinding, V extends BaseView, P extends BasePresenter<V>>
        extends BaseActivity<D, V, P> implements ListFilterContract.View {


    protected boolean isFilterLayoutIn;
    /**
     * 显示背景
     */
    @Override
    public void showGrayBg() {
        if (getGrayBgView().getVisibility() == View.GONE) {
            AnimationUtils.mackAlphaAnimation(getGrayBgView(), true).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    getGrayBgView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    /**
     * 关闭背景
     */
    @Override
    public void closeGrayBg() {
        if (getGrayBgView().getVisibility() == View.VISIBLE) {
            AnimationUtils.mackAlphaAnimation(getGrayBgView(), false).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    getGrayBgView().setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    /**
     * 筛选条件布局开始进入
     */
    @Override
    public void layoutStartIn() {
        if (!isFilterLayoutIn) {
            AnimationUtils.mackTranslationYAnimation(getFilterLayout(), getFilterLayoutHeight(), true).start();
            isFilterLayoutIn = true;
        } else if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            ViewUtils.viewIn(getFilterLayout());
        }
    }

    /**
     * 筛选条件的布局出来
     */
    @Override
    public void layoutStartOut() {
        if (isFilterLayoutIn) {
            AnimationUtils.mackTranslationYAnimation(getFilterLayout(), getFilterLayoutHeight(), false).start();
            isFilterLayoutIn = false;
        }
    }


    /**
     * 获取背景的View
     */
    protected abstract View getGrayBgView();

    /**
     * 获取用于筛选的布局
     *
     * @return
     */
    protected abstract View getFilterLayout();

    /**
     * 获取筛选布局的高度
     *
     * @return
     */
    protected abstract int getFilterLayoutHeight();
}
