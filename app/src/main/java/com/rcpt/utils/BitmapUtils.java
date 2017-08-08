package com.rcpt.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.rcpt.R;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：
 */

public class BitmapUtils {

    public static class BitmapHolder {
        public Bitmap bitmap;
        public float width;
        public float height;
    }

    /**
     * 生成文字图片
     *
     * @return
     */
    public static BitmapHolder createBitmap(String text, float textSize, Context context) {
        float paddingTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());
        float paddingLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics());
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        //设置文字的大小
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, context.getResources().getDisplayMetrics()));
        //设置画笔的颜色
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        //计算Bitmap的宽高
        float width = paint.measureText(text) + paddingLeft * 2;
        float height = Math.abs(paint.descent()) + Math.abs(paint.ascent()) + paddingTop * 2;

        // 计算Baseline绘制的起点X轴坐标
        float baseX = paddingLeft + 0;

        // 计算Baseline绘制的Y坐标
        float baseY = height / 2 - ((paint.descent() + paint.ascent()) / 2);

        Drawable bgDrawable = ContextCompat.getDrawable(context, R.drawable.bg_stroke_blue);
        Bitmap bp = drawableToBitamp((int) width, (int) height, bgDrawable);

        Canvas c = new Canvas(bp);
        c.drawBitmap(bp, 0, 0, paint);
        // c.drawBitmap(drawableToBitamp((int) width, (int) height, bgDrawable), new Rect(0, 0, (int) width, (int) height), new RectF(10, 15, 0, 20), paint);
        c.drawText(text, baseX, baseY, paint);

        c.save(Canvas.ALL_SAVE_FLAG);//保存
        c.restore();//
        BitmapHolder holder = new BitmapHolder();
        holder.bitmap = bp;
        holder.width = width;
        holder.height = height;
        return holder;
    }

    private static Bitmap drawableToBitamp(int width, int height, Drawable drawable) {
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

}
