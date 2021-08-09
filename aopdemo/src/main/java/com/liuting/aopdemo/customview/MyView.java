package com.liuting.aopdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * 作者:admin on 2020/4/12 10:08
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo.customview
 * TODO:
 */
public class MyView extends View {

    private static final String TAG="View事件测试";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyView的dispatchTouchEvent收到ACTION_DOWN事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyView的dispatchTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyView的dispatchTouchEvent收到ACTION_UP事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MyView的dispatchTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "MyView的dispatchTouchEvent走default分支");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyView的onTouchEvent收到ACTION_DOWN事件");
                return true;
                //break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyView的onTouchEvent收到ACTION_MOVE事件");
                return false;
            //break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyView的onTouchEvent收到ACTION_UP事件");
                return false;
            //break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "MyView的onTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "MyView的onTouchEvent走default分支");
                break;
        }
        return super.onTouchEvent(event);
    }
}
