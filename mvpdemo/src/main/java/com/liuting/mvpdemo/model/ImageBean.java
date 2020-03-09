package com.liuting.mvpdemo.model;

import android.graphics.Bitmap;

/**
 * 作者:admin on 2020/3/8 11:55
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.model
 * TODO:
 */
public class ImageBean {
    //图片请求地址
    private String requestPath;
    private Bitmap mBitmap;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
