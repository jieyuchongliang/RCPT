/**
 * Copyright 2016 smartbetter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rcpt.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gc on 2016/11/6.
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 检查是否存在SD卡
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建目录
     *
     * @param context
     * @param dirName 文件夹名称
     * @return
     */
    public static File createFileDir(Context context, String dirName) {
        String filePath;
        // 如SD卡已存在，则存储；反之存在data目录下
        if (hasSdcard()) {
            // SD卡路径
            filePath = Environment.getExternalStorageDirectory() + File.separator + dirName;
        } else {
            filePath = context.getCacheDir().getPath() + File.separator + dirName;
        }
        File destDir = new File(filePath);
        if (!destDir.exists()) {
            boolean isCreate = destDir.mkdirs();
            Log.i("FileUtils", filePath + " has created. " + isCreate);
        }
        return destDir;
    }

    /**
     * 删除文件（若为目录，则递归删除子目录和文件）
     *
     * @param file
     * @param delThisPath true代表删除参数指定file，false代表保留参数指定file
     */
    public static void delFile(File file, boolean delThisPath) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                int num = subFiles.length;
                // 删除子目录和文件
                for (int i = 0; i < num; i++) {
                    delFile(subFiles[i], true);
                }
            }
        }
        if (delThisPath) {
            file.delete();
        }
    }

    /**
     * 获取文件大小，单位为byte（若为目录，则包括所有子目录和文件）
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    int num = subFiles.length;
                    for (int i = 0; i < num; i++) {
                        size += getFileSize(subFiles[i]);
                    }
                }
            } else {
                size += file.length();
            }
        }
        return size;
    }

    /**
     * 保存Bitmap到指定目录
     *
     * @param dir 目录
     * @param fileName 文件名
     * @param bitmap
     */
    public static void saveBitmap(File dir, String fileName, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        File file = new File(dir, fileName);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断某目录下文件是否存在
     *
     * @param dir 目录
     * @param fileName 文件名
     * @return
     */
    public static boolean isFileExists(File dir, String fileName) {
        return new File(dir, fileName).exists();
    }
    /**
     * 将字节写入到文件
     *
     * @param file
     * @param data
     */
    private static void writeFile(File file, byte[] data) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取相应文件，返回文件中的字节
     *
     * @param file
     * @return
     */
    private static byte[] readFile(File file) {
        if (!file.exists()) {
            System.out.println("当前文件不存在");
            return null;
        }

        if (file.length() > Integer.MAX_VALUE) {
            System.out.println("文件过大");
            return null;
        }

        FileInputStream inputStream = null;
        BufferedInputStream bfInputStream = null;
        byte[] inputData = new byte[(int) file.length()];
        try {
            inputStream = new FileInputStream(file);
            bfInputStream = new BufferedInputStream(inputStream);

            int tempByte = -1;
            int index = 0;
            //这种读取效率特别快，2M左右的文件，读取时间在100毫秒左右
            while ((tempByte = bfInputStream.read()) != -1) {
                inputData[index] = (byte) tempByte;
                index++;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return inputData;
    }
}