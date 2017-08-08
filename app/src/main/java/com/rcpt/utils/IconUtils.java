package com.rcpt.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class IconUtils {


    public static Typeface getIcon(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "rcpt.ttf");
    }

}
