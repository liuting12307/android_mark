package com.liuting.aopdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者:admin on 2020/4/12 10:07
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo.customview
 * TODO:
 */
public class MyFrameLayout extends FrameLayout {
    private static final String TAG="View事件测试";
    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyFrameLayout的dispatchTouchEvent收到ACTION_DOWN事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyFrameLayout的dispatchTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyFrameLayout的dispatchTouchEvent收到ACTION_UP事件");
               // return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MyFrameLayout的onTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "MyFrameLayout的dispatchTouchEvent走default分支");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyFrameLayout的onInterceptTouchEvent收到ACTION_DOWN事件");
               // return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyFrameLayout的onInterceptTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyFrameLayout的onInterceptTouchEvent收到ACTION_UP事件");
                break;
                //return true;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MyFrameLayout的onInterceptTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "MyFrameLayout的onInterceptTouchEvent走default分支");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyFrameLayout的onTouchEvent收到ACTION_DOWN事件");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyFrameLayout的onTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyFrameLayout的onTouchEvent收到ACTION_UP事件");
                //return false;
             break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MyFrameLayout的onTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "MyFrameLayout的onTouchEvent走default分支");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
