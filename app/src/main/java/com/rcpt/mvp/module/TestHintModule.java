package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.bean.TestHintBean;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public class TestHintModule {


    public TestHintBean getPageData(Context context, String type) {
        TestHintBean bean = new TestHintBean();
        switch (type) {
            case Constant.TEST_TYPE_MAJOR://专业知识测试
                bean.setHint(context.getString(R.string.test_hint_type_major));
                bean.setShowRes(R.mipmap.bg_test_major);
                bean.setName("全面和符合要求的测试");
                break;
            case Constant.TEST_TYPE_ABILITY://能力测试
                bean.setHint(context.getString(R.string.test_hint_type_ability));
                bean.setShowRes(R.mipmap.bg_test_ability);
                bean.setName("工作胜任的能力强弱");
                break;
            case Constant.TEST_TYPE_PROFESSION://职业倾向测试
                bean.setHint(context.getString(R.string.test_hint_type_profession));
                bean.setShowRes(R.mipmap.bg_test_profession);
                bean.setName("心理特征和行为倾向");
                break;
        }
        return bean;
    }


}
