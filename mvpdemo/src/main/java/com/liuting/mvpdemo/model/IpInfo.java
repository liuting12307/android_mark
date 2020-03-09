package com.liuting.mvpdemo.model;

import androidx.annotation.NonNull;

/**
 * 作者:admin on 2019/11/17 16:05
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.model
 * TODO:
 */
public class IpInfo {
    private int code;
    private IpData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public IpData getData() {
        return data;
    }

    public void setData(IpData data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return data.toString();
    }
}
