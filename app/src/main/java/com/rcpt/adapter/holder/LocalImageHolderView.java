package com.rcpt.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.rcpt.R;
import com.rcpt.http.api.ApiService;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：首页轮播图的图片加载帮助类
 */

public class LocalImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        //王强：这里之前是ImageView.ScaleType.CENTER_CROP，我给改成了FIT_XY.当是CENTER_CROP时，图片有的显示不全。
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, String data) {
        data =  ApiService.IMAGE_BASE + data;
        Glide.with(context).load(data).error(R.mipmap.ic_launcher).into(imageView);
    }
}