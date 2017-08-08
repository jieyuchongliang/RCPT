package com.rcpt.mvp.module;

import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by 860116021 on 2017/3/14.
 */

public class EmailGetModule {

    private  String email;
    private String verifyCode;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
            hintString.setSpan(new ForegroundColorSpan(phoneColor), 0, 13, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            hintString.setSpan(new AbsoluteSizeSpan(size, true), 0, 13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        }
        return hintString;
    }

    public CharSequence setSaveNewPasswordFormat(String text, String keyword, @ColorInt int phoneColor, int size) {
        SpannableString formatString = new SpannableString(text);
        if (text.contains(keyword)) {
            int indexKeyword = text.indexOf(keyword);
            formatString.setSpan(new ForegroundColorSpan(phoneColor), indexKeyword + 2, indexKeyword + 2 + email.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            formatString.setSpan(new AbsoluteSizeSpan(size, true), indexKeyword + 2, indexKeyword + 2 + email.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        }
        return formatString;
    }
}
