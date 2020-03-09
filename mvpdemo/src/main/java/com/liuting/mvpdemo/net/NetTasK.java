package com.liuting.mvpdemo.net;

import com.liuting.mvpdemo.LoadTasksCallBack;

/**
 * 作者:admin on 2019/11/17 20:02
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.net
 * TODO:
 */
public interface NetTasK<T> {
    void execute(T data, LoadTasksCallBack callBack);
}
