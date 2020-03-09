package com.liuting.mvpdemo;

/**
 * 作者:admin on 2019/11/17 20:05
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo
 * TODO:
 */
public interface LoadTasksCallBack<T> {
    void onSuccess(T t);
    void onStart();
    void onFailed();
    void onFinish();
}
