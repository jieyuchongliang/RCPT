package com.rcpt.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.rcpt.R;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class EditUtils {

    private int mMaxInputNum;//最大输入数量
    private TextWatcher mTextWatcher = new TextWatcher();
    private EditText mEditText;
    private boolean isInputOutOf;//是否输入越界
    private TextView mShowInputNum;//用于显示用户输入的文字长度的TextView
    private Context mContext;

    /**
     * TextWatcher的构造器
     */
    public static class WatcherBuilder {

        private final WatcherParams P;

        public WatcherBuilder() {
            P = new WatcherParams();
        }

        /**
         * 设置最大输入的长度
         *
         * @param inputNum
         * @return
         */
        public WatcherBuilder setMaxInputNum(int inputNum) {
            P.mMaxInputNum = inputNum;
            return this;
        }

        /**
         * 绑定需要监听的EditText
         *
         * @param editText
         * @return
         */
        public WatcherBuilder bindEditText(EditText editText) {
            P.mEditText = editText;
            return this;
        }

        /**
         * 设置显示输入数量的TextView
         *
         * @param showInputNumTextView
         * @return
         */
        public WatcherBuilder setShowInputNumTextView(TextView showInputNumTextView) {
            P.mShowInputNum = showInputNumTextView;
            return this;
        }

        /**
         * 创建监听器
         *
         * @return
         */
        public EditUtils create() {
            EditUtils editUtils = new EditUtils();
            editUtils.setMaxInputNum(P.mMaxInputNum);
            editUtils.attachWatcherEditView(P.mEditText, P.mShowInputNum);
            return editUtils;
        }

    }


    public void setMaxInputNum(int maxInputNum) {
        mMaxInputNum = maxInputNum;
    }

    /**
     * 将需要监听的EditText绑定上TextWatcher
     *
     * @param editText
     * @param textView
     */
    public void attachWatcherEditView(EditText editText, TextView textView) {
        mEditText = editText;
        mShowInputNum = textView;
        mContext = mEditText.getContext();
        mEditText.addTextChangedListener(mTextWatcher);
    }

    /**
     * 解除绑定
     */
    public void detachWatcherEditView() {
        mEditText.removeTextChangedListener(mTextWatcher);
    }

    /**
     * 获取当前的输入内容是否输入越界
     *
     * @return
     */
    public boolean isInputOutOf() {
        return isInputOutOf;
    }

    private class TextWatcher implements android.text.TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > mMaxInputNum) {
                if (mShowInputNum != null)
                    if (mShowInputNum.getTextColors().getDefaultColor() == ContextCompat.getColor(mContext, R.color.colorTextBlack)) {
                        mShowInputNum.setTextColor(ContextCompat.getColor(mContext, R.color.colorRed));
                    }
                isInputOutOf = true;
            } else {
                if (mShowInputNum != null)
                    if (mShowInputNum.getTextColors().getDefaultColor() == ContextCompat.getColor(mContext, R.color.colorRed)) {
                        mShowInputNum.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextBlack));
                    }
                isInputOutOf = false;
            }
            if (mShowInputNum != null)
                mShowInputNum.setText(mContext.getString(R.string.max_input_length, s.length(), mMaxInputNum));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private static class WatcherParams {
        int mMaxInputNum;//最大输入数量
        EditText mEditText;//用于监听的EditText
        TextView mShowInputNum;//用于显示用户输入的文字长度的TextView
    }

}
