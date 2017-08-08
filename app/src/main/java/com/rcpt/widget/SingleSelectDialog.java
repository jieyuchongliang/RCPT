package com.rcpt.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rcpt.R;

/**
 * Created by lvzp on 2017/3/20.
 * 类描述：单项选择的Dialog
 */

public class SingleSelectDialog extends Dialog {

    private TextView mTitle;
    private RadioGroup mRGroup;

    private OnClickSelectCallback mCallback;

    private RadioButton mRBtnOne;
    private RadioButton mRBtnTwo;

    public SingleSelectDialog(@NonNull Context context) {
        super(context);
        init();
    }


    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_single_select);
        mRGroup = (RadioGroup) findViewById(R.id.radio_select_group);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mRBtnOne = (RadioButton) findViewById(R.id.rBtn_select_one);
        mRBtnTwo = (RadioButton) findViewById(R.id.rBtn_select_two);

        mRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rBtn_select_one:
                        if (mCallback != null) {
                            mCallback.onDataCallback(mRBtnOne.getText().toString());
                        }
                        break;
                    case R.id.rBtn_select_two:
                        if (mCallback != null) {
                            mCallback.onDataCallback(mRBtnTwo.getText().toString());
                        }
                        break;
                }
                cancel();
            }
        });
    }

    public void setSelectData(String... selectData) {
        if (selectData.length == 2) {
            mRBtnOne.setText(selectData[0]);
            mRBtnTwo.setText(selectData[1]);
        }
    }

    public void setOnClickSelectCallback(OnClickSelectCallback callback) {
        mCallback = callback;
    }

    public void setTitleText(String titleText) {
        if (!TextUtils.isEmpty(titleText)) {
            mTitle.setText(titleText);
        }
    }

    public void setSelectItemName(String name) {
        if (TextUtils.isEmpty(name))
            return;
        if (name.equals(mRBtnOne.getText().toString())) {
            mRBtnOne.setChecked(true);
        } else if (name.equals(mRBtnTwo.getText().toString())) {
            mRBtnTwo.setChecked(true);
        }
    }

    public interface OnClickSelectCallback {
        /**
         * 当点击单项选择的数据后的回调
         *
         * @param data
         */
        void onDataCallback(String data);

    }

}
