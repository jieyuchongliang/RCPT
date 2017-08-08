package com.rcpt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by lvzp on 2017/3/13.
 * 类描述：带有滑动监听的ScrollView
 */

public class ObservableScrollView extends ScrollView {

    private OnScrollChangedListener mOnScrollChangedListener = null;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface OnScrollChangedListener {

        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }

}
