package com.rcpt.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcpt.R;

/**
 * Created by lvzp on 2017/3/31.
 * 类描述：一个包含左侧带图标的标题tab
 */

public class TitleTabView extends LinearLayout {

    private static final int DEFAULT_VALUE_PADDING = 10;
    private static final int DEFAULT_VALUE_IMAGE_SIZE = 18;
    private static final int DEFAULT_VALUE_TITLE_LEFT_MARGIN = 5;
    private static final int DEFAULT_VALUE_TITLE_TEXT_SIZE = 16;

    private ImageView mIvLeftIcon;
    private TextView mTvTitle;

    private Drawable mLeftImage;
    private CharSequence mTitleName;

    private int mTitleLeftMargin;
    private int mImageSize;
    private int mTitleTextSize;
    private ColorStateList mTitleTextColor;

    public TitleTabView(Context context) {
        super(context);
        init(null, 0);
    }


    public TitleTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TitleTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void setLeftIconImage(Drawable drawable) {
        mLeftImage = drawable;
        if (mLeftImage != null && mIvLeftIcon != null) {
            mIvLeftIcon.setImageDrawable(mLeftImage);
        }
    }

    public void setLeftIconImage(@DrawableRes int drawableRes) {
        setLeftIconImage(ContextCompat.getDrawable(getContext(), drawableRes));
    }

    public void setTitleName(CharSequence str) {
        mTitleName = str;
        if (mTitleName != null && mTvTitle != null) {
            mTvTitle.setText(mTitleName);
        }
    }

    public void setTitleLeftMargin(int leftMargin) {
        mTitleLeftMargin = leftMargin;
        if (mIvLeftIcon != null) {
            LayoutParams layoutParams = (LayoutParams) mIvLeftIcon.getLayoutParams();
            layoutParams.leftMargin = mTitleLeftMargin;
            mIvLeftIcon.setLayoutParams(layoutParams);
        }
    }

    public void setImageSize(int imageSize) {
        mImageSize = imageSize;
        if (mIvLeftIcon != null) {
            LayoutParams lp = (LayoutParams) mIvLeftIcon.getLayoutParams();
            lp.width = mImageSize;
            lp.height = mImageSize;
            mIvLeftIcon.setLayoutParams(lp);
        }
    }

    public void setTitleTextColor(ColorStateList titleTextColor) {
        mTitleTextColor = titleTextColor;
        if (mTitleTextColor != null && mTvTitle != null) {
            mTvTitle.setTextColor(mTitleTextColor);
        }
    }

    //************************内部处理的方法**********************


    private void init(AttributeSet attrs, int defStyleAttr) {
        //设置方向为横向
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(Color.WHITE);
        initDefaultValue();
        initViews();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleTabView, defStyleAttr, 0);
            int count = a.getIndexCount();
            for (int i = 0; i < count; i++) {
                int index = a.getIndex(i);
                switch (index) {
                    case R.styleable.TitleTabView_titleLeftIcon:
                        mLeftImage = a.getDrawable(index);
                        break;
                    case R.styleable.TitleTabView_titleName:
                        mTitleName = a.getText(index);
                        break;
                    case R.styleable.TitleTabView_titleLeftMargin:
                        mTitleLeftMargin = a.getDimensionPixelSize(index, DEFAULT_VALUE_TITLE_LEFT_MARGIN);
                        break;
                    case R.styleable.TitleTabView_titleImageSize:
                        mImageSize = a.getDimensionPixelSize(index, DEFAULT_VALUE_IMAGE_SIZE);
                        break;
                    case R.styleable.TitleTabView_titleTextSize:
                        mTitleTextSize = a.getDimensionPixelSize(index, DEFAULT_VALUE_TITLE_TEXT_SIZE);
                        break;
                    case R.styleable.TitleTabView_titleTextColor:
                        mTitleTextColor = a.getColorStateList(index);
                        break;
                }

            }

            a.recycle();
        }
        setLeftIconImage(mLeftImage);
        setTitleName(mTitleName);
        setTitleLeftMargin(mTitleLeftMargin);
        setImageSize(mImageSize);
        setTitleTextSize(mTitleTextSize);
        setTitleTextColor(mTitleTextColor);
    }

    private void initDefaultValue() {

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_VALUE_PADDING, getResources().getDisplayMetrics());
        mImageSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_VALUE_IMAGE_SIZE, getResources().getDisplayMetrics());
        mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_VALUE_TITLE_TEXT_SIZE, getResources().getDisplayMetrics());
        mTitleLeftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_VALUE_TITLE_LEFT_MARGIN, getResources().getDisplayMetrics());
        setPadding(padding, padding, padding, padding);
    }

    private void initViews() {

        mIvLeftIcon = new ImageView(getContext());
        LayoutParams ivLp = new LayoutParams(mImageSize, mImageSize);
        mIvLeftIcon.setLayoutParams(ivLp);


        mTvTitle = new TextView(getContext());
        LayoutParams tvLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLp.leftMargin = mTitleLeftMargin;

        mTvTitle.setTextSize(mTitleTextSize);
        mTvTitle.setLayoutParams(tvLp);

        addView(mIvLeftIcon);
        addView(mTvTitle);
    }

    private void setTitleTextSize(int titleTextSize) {
        mTitleTextSize = titleTextSize;
        if (mTvTitle != null) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        }
    }
}
