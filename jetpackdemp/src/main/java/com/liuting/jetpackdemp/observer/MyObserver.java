package com.liuting.jetpackdemp.observer;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 作者:admin on 2020/6/20 23:08
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.jetpackdemp.observer
 * TODO:
 */
public class MyObserver implements LifecycleObserver {

    private static final String TAG="MyObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        Log.d(TAG,"Lifecycle call onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(){
        Log.d(TAG,"Lifecycle call onStart");
    }
}
