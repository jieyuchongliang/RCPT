package com.rcpt.mvp.module;

import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class PhoneGetModule {

    private String phoneNum;
    private String verifyCode;
    private String newPassword;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public CharSequence setSendVerifyFormat(String text, @ColorInt int keywordColor, @ColorInt int phoneColor, int size, String... keys) {
        SpannableString hintString = new SpannableString(text);
        String keyword = keys[0];
        if (text.contains(keyword)) {
            int indexKeyword = text.indexOf(keyword);
            int indexPhone = text.indexOf(keys[1]);
            hintString.setSpan(new ForegroundColorSpan(keywordColor), indexKeyword, indexKeyword + keyword.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            hintString.setSpan(new ForegroundColorSpan(phoneColor), indexPhone + 1, indexPhone + 4 + phoneNum.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            hintString.setSpan(new AbsoluteSizeSpan(size, true), indexPhone + 1, indexPhone + 4 + phoneNum.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        }
        return hintString;
    }

/*    public CharSequence setSaveNewPasswordFormat(String text, String keyword, @ColorInt int phoneColor, int size) {
        SpannableString formatString = new SpannableString(text);
        if (text.contains(keyword)) {
            int indexKeyword = text.indexOf(keyword);
            formatString.setSpan(new ForegroundColorSpan(phoneColor), indexKeyword + 2, indexKeyword + 2 + phoneNum.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            formatString.setSpan(new AbsoluteSizeSpan(size, true), indexKeyword + 2, indexKeyword + 2 + phoneNum.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        }
        return formatString;
    }*/

}
