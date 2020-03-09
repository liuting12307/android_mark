package com.liuting.mvpdemo;

import android.app.Application;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * 作者:admin on 2019/11/17 15:51
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo
 * TODO:
 */
public class MvpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpFinalConfiguration.Builder builder=new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
