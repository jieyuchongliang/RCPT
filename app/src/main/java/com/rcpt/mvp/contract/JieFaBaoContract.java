package com.rcpt.mvp.contract;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.ViewPagerWithGroupContract;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public interface JieFaBaoContract {

    interface View extends BaseView, ViewPagerWithGroupContract.View {

    }

    interface Presenter extends ViewPagerWithGroupContract.Presenter {

    }

}
