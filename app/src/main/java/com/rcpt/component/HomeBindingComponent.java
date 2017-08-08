package com.rcpt.component;

import android.databinding.BindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.github.premnirmal.textcounter.CounterView;
import com.rcpt.bean.HomeNewsFollowBean;
import com.rcpt.utils.BitmapUtils;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class HomeBindingComponent {
    @BindingAdapter({"setFollowTitle"})
    public static void setNewsFollowTitle(TextView tv, HomeNewsFollowBean bean) {
        if (bean != null) {

            SpannableString span = new SpannableString("a    " + bean.getTitle());
            BitmapUtils.BitmapHolder holder = BitmapUtils.createBitmap(bean.getFromWhere(), 12, tv.getContext());
            //设置图片
            Drawable drawable = new BitmapDrawable(holder.bitmap);
            drawable.setBounds(0, 0, (int) holder.width, (int) holder.height);
            span.setSpan(new ImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tv.setText(span);
        }
    }

    @BindingAdapter({"setProjectProgress"})
    public static void setProjectProgress(CounterView view, int progressValue) {
        float increment = (progressValue - 1) / 30;
        view.setEndValue(progressValue);
        view.setIncrement(increment);
        view.start();
    }
}
