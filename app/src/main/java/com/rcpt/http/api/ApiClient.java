package com.rcpt.http.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.MenuBean;
import com.rcpt.http.RetrofitManager;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：
 */

public class ApiClient {


    /**
     * 获取请求请求网络用的Service
     *
     * @return
     */
    public static ApiService getApiService() {
        return RetrofitManager.getInstance().getRetrofit().create(ApiService.class);
    }

    /**
     * 单图片上传的Body
     * 在{@link ApiService}中需要如下定义接口
     * ----@POST("Api/Hx/AddHx")
     * ----@Multipart
     * Observable<> uploadGroupCreate(@Part MultipartBody.Part photo);
     *
     * @param file
     * @return
     */
    public static MultipartBody.Part getFileBody(String part, File file) {
        Log.d("需要上传图片的大小和路径", "图片大小：" + file.length() + "图片路径：" + file.getPath());
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        String fileName = file.getName();
        return MultipartBody.Part.createFormData(part, fileName, photoRequestBody);
    }


    public static <T> Map<String, String> createBodyMap(T t) {
        Class tClass = t.getClass();
        Map<String, String> bodyMap = new HashMap<>();
        try {
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);// 修改访问权限
                if (field.getType() == java.lang.String.class) {
                    if (field.get(t) == null)
                        continue;
                    bodyMap.put(field.getName(), String.valueOf(field.get(t)));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bodyMap;
    }

    public static <T> void menuDataToList(T bean, List<? extends MenuBean> list) {
        if (bean == null || list == null) return;
        Class cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fileName = field.getName();
                for (MenuBean inputMenuBean : list) {
                    if (inputMenuBean.getSqlName().equals(fileName)) {
                        field.setAccessible(true);
                        String value = (String) field.get(bean);
                        inputMenuBean.setMenuValue(value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String> checkMenuListIsEmpty(List<? extends MenuBean> list) {
        List<String> emptyValueList = new ArrayList<>();
        for (MenuBean menuBean : list) {
            //判断当前的菜单是否为isEdit(说明不需要对其进行为空判断) ，如果有则跳过循环
            if (menuBean instanceof InputMenuBean) {
                if (((InputMenuBean) menuBean).isEdit()) {
                    continue;
                }
            }
            //判断menuValue为空，并且需要上传，且上传的value同时为空时，确定为空
            if (menuBean.isCheckable() && TextUtils.isEmpty(menuBean.getMenuValue())) {
                if (TextUtils.isEmpty(menuBean.getUpdateValue())) {
                    emptyValueList.add(menuBean.getMenuName());
                    return emptyValueList;
                }
            } else {
                if (TextUtils.isEmpty(menuBean.getMenuValue())) {
                    emptyValueList.add(menuBean.getMenuName());
                    return emptyValueList;
                }
            }
        }
        return emptyValueList;
    }

    public static <T> void menuListToData(List<? extends MenuBean> list, T bean) {
        if (bean == null || list == null) return;
        for (MenuBean menuBean : list) {
            Class cls = bean.getClass();
            Field field;
            try {
                field = cls.getDeclaredField(menuBean.getSqlName());
                field.setAccessible(true);
                if (menuBean.isCheckable() && menuBean.getUpdateValue() == null)
                    continue;
                field.set(bean, menuBean.getUpdateValue() == null ? menuBean.getMenuValue() : menuBean.getUpdateValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片压缩处理后进行上传
     *
     * @param file
     * @param listener
     */
    public static void pictureCompression(Context context, File file, OnCompressListener listener) {
        Luban.get(context)
                .load(file)                     //传人要压缩的图片
                .putGear(Luban.THIRD_GEAR)//设定压缩档次，默认三挡
                //.setFilename(context.getCacheDir().getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg")//设置上传后的名称
                .setCompressListener(listener).launch();
    }

    /**
     * 带有检测功能的图片压缩
     *
     * @param context
     * @param file
     * @param listener
     */
    public static void pictureCompressionWhiteCheck(Context context, File file, OnCompressListener listener) {
        if (file.length() >= 1024 * 1024 * 2) {
            pictureCompression(context, file, listener);
        } else {
            listener.onSuccess(file);
        }
    }

}
