package com.rcpt.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rcpt.R;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public class ViewFactory {


    public static void createMajorTypeTab(Context context, RadioGroup group, String tabValue) {
        RadioButton button = (RadioButton) LayoutInflater.from(context).inflate(R.layout.layout_tab_test_major_type, group, false);
        button.setText(tabValue);
        group.addView(button);
    }


}
