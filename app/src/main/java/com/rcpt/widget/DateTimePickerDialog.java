package com.rcpt.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.Window;

import java.util.Calendar;

/**
 * 作者：吕振鹏
 * 创建时间：04月16日
 * 时间：21:38
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class DateTimePickerDialog extends AlertDialog implements
        DialogInterface.OnClickListener {
    private DateTimePicker mDateTimePicker;
    private Calendar mDate = Calendar.getInstance();
    private OnDateTimeSetListener mOnDateTimeSetListener;

    @SuppressWarnings("deprecation")
    public DateTimePickerDialog(Context context, long date) {
        super(context);
        mDateTimePicker = new DateTimePicker(context,date);
        setView(mDateTimePicker);
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(android.R.style.Animation_Dialog);
        }
        /*
         *实现接口，实现里面的方法
         */
        mDateTimePicker
                .setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
                    @Override
                    public void onDateTimeChanged(DateTimePicker view,
                                                  int year, int month, int day, int hour, int minute) {
                        mDate.set(Calendar.YEAR, year);
                        mDate.set(Calendar.MONTH, month);
                        mDate.set(Calendar.DAY_OF_MONTH, day);
                        mDate.set(Calendar.HOUR_OF_DAY, hour);
                        mDate.set(Calendar.MINUTE, minute);
                        mDate.set(Calendar.SECOND, 0);
                        /**
                         * 更新日期
                         */
                        updateTitle(mDate.getTimeInMillis());
                    }
                });

        setButton(DialogInterface.BUTTON_POSITIVE, "设置", this);
        setButton(DialogInterface.BUTTON_NEUTRAL, "取消", (OnClickListener) null);
        mDate.setTimeInMillis(date);
        updateTitle(mDate.getTimeInMillis());
    }

    /*
     *接口回調
     *控件 秒数
     */
    public interface OnDateTimeSetListener {
        void OnDateTimeSet(AlertDialog dialog, long date);
    }

    /**
     * 更新对话框日期
     *
     * @param date
     */
    private void updateTitle(long date) {
        setTitle(DateFormat.format("yyyy年MM月dd日 ccc HH:mm",date));
    }

    /*
     * 对外公开方法让Activity实现
     */
    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }

    public void onClick(DialogInterface arg0, int arg1) {
        if (mOnDateTimeSetListener != null) {
            mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
        }
    }
}