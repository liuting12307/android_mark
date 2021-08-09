package com.liuting.aopdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 作者:admin on 2020/3/29 11:06
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo
 * TODO:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
