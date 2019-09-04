package com.liuting.record;

import android.app.Application;

import com.liuting.record.exception.CrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 作者:admin on 2019/8/31 14:11
 * 邮箱:liuting@xinguodu.com
 * 项目名：liuting_project
 * 包名：com.liuting.record
 * TODO:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
