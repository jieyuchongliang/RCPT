package com.rcpt.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import org.apache.commons.lang.time.DurationFormatUtils;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public class CountDownTimerUtils extends CountDownTimer {

    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private TextView mTextView;
    private String mTimeFormat = DEFAULT_TIME_FORMAT;
    private OnCountDownFinishListener mListener;

    public CountDownTimerUtils(long millisInFuture) {
        super(millisInFuture, 1000);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        updateUiTime(millisUntilFinished);
    }

    private void updateUiTime(long millisUntilFinished) {
        String timeStr = DurationFormatUtils.formatDuration(millisUntilFinished, mTimeFormat);
        if (mTextView != null)
            mTextView.setText(timeStr);
    }

    public void setTimeFormat(String format) {
        mTimeFormat = format;
    }

    public void bindTextView(TextView tv) {
        mTextView = tv;
    }

    public void setOnCountDownFinishListener(OnCountDownFinishListener li) {
        mListener = li;
    }

    @Override
    public void onFinish() {
        updateUiTime(0);
        if (mListener != null)
            mListener.onFinish();
    }

    public interface OnCountDownFinishListener {
        void onFinish();
    }

}
