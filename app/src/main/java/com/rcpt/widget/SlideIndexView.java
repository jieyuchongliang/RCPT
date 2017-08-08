package com.rcpt.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rcpt.R;

import java.lang.ref.WeakReference;

/**
 * Created by lvzp on 2017/2/17.
 * 类描述：
 */

public class SlideIndexView extends View {

    private final static String TAG = SlideIndexView.class.getSimpleName();

    private Paint mPaint;
    private RectF mLineRect = new RectF();
    private float mRoundRadius;

    private ViewGroup mTitleGroup;
    private ViewPager mViewPager;

    // 控制动画
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private Interpolator mEndInterpolator = new LinearInterpolator();

    private PageChangeListener mPageChangeListener;
    private CheckChangeListener mCheckChangeListener;

    private boolean isTabClick;

    private
    @ColorInt
    int mIndexColor;

    public SlideIndexView(Context context) {
        this(context, null);
    }

    public SlideIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        initDefaultValue();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlideIndexView, defStyleAttr, 0);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            if (index == R.styleable.SlideIndexView_indexColor) {
                mIndexColor = ta.getColor(index, mIndexColor);
            }
        }
        ta.recycle();

        mPaint.setColor(mIndexColor);
    }

    private void initDefaultValue() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mCheckChangeListener = new CheckChangeListener();

        mIndexColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mLineRect, mRoundRadius, mRoundRadius, mPaint);
    }

    public void bindViewPageAndTitle(ViewPager pager, ViewGroup viewGroup) {
        if (viewGroup != null && pager != null) {
            mTitleGroup = viewGroup;
            mViewPager = pager;

            if (mPageChangeListener == null) {
                mPageChangeListener = new PageChangeListener(mTitleGroup);
            }

            mViewPager.addOnPageChangeListener(mPageChangeListener);

            if (mCheckChangeListener != null && mTitleGroup instanceof RadioGroup) {
                ((RadioGroup) mTitleGroup).setOnCheckedChangeListener(mCheckChangeListener);
            }
        }

    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        private int mStartColor;
        private int mEndColor;
        private ArgbEvaluator mArgbEvaluator;
        private WeakReference<ViewGroup> mTitleGroup;

        private PageChangeListener(ViewGroup group) {
            mTitleGroup = new WeakReference<>(group);
            mStartColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
            mEndColor = ContextCompat.getColor(getContext(), R.color.colorGray);
            mArgbEvaluator = new ArgbEvaluator();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            final ViewGroup tabLayout = mTitleGroup.get();
            if (tabLayout != null) {
                int startColor = (int) mArgbEvaluator.evaluate(positionOffset, mStartColor, mEndColor);
                int endColor = (int) mArgbEvaluator.evaluate(1 - positionOffset, mStartColor, mEndColor);

                int childCount = mTitleGroup.get().getChildCount();

                View startView = mTitleGroup.get().getChildAt(position);
                View endView = null;
                if (startView instanceof TextView) {
                    ((TextView) startView).setTextColor(startColor);
                }
                if (position + 1 < childCount) {
                    endView = mTitleGroup.get().getChildAt(position + 1);
                    if (endView instanceof TextView) {
                        ((TextView) endView).setTextColor(endColor);
                    }
                }

                float leftX = 0;
                float nextLeftX = 0;
                float rightX = 0;
                float nextRightX = 0;

                if (endView != null) {
                    nextLeftX = endView.getLeft();
                    nextRightX = endView.getRight();
                }
                leftX = startView.getLeft();
                rightX = startView.getRight();


                mLineRect.left = leftX + (nextLeftX - leftX) * mStartInterpolator.getInterpolation(positionOffset);
                mLineRect.right = rightX + (nextRightX - rightX) * mEndInterpolator.getInterpolation(positionOffset);
                mLineRect.top = 0;
                mLineRect.bottom = getHeight();

                invalidate();
            }

        }

        @Override
        public void onPageSelected(int position) {
            final ViewGroup tabLayout = mTitleGroup.get();
            if (tabLayout != null && position < tabLayout.getChildCount()) {
                View childView = tabLayout.getChildAt(position);
                if (childView != null && childView instanceof RadioButton) {
                    ((RadioButton) childView).setChecked(true);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class CheckChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int currentPosition = 0;
            int groupCount = group.getChildCount();
            for (int i = 0; i < groupCount; i++) {
                View view = group.getChildAt(i);
                if (checkedId == view.getId()) {
                    currentPosition = i;
                    break;
                }
            }
            mViewPager.setCurrentItem(currentPosition);
        }
    }
}
