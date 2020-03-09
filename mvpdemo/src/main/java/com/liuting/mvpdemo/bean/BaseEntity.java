package com.liuting.mvpdemo.bean;

/**
 * 作者:admin on 2020/3/8 15:09
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.bean
 * TODO:
 */
public class BaseEntity {
    private int code;
    private boolean success;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
