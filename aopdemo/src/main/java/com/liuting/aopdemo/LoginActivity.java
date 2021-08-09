package com.liuting.aopdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liuting.aopdemo.customview.MyView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者:admin on 2020/3/7 22:25
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo
 * TODO:
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "View事件测试";

    private MyView mMyView;
    private ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // Glide.with(this).load("").into(mImageView);
        initView();
        initListener();
    }

    private void initListener() {
        mMyView.setOnClickListener(v -> Log.d(TAG, "myview的onClick事件被点击"));
    }

    private void initView() {
        mMyView = findViewById(R.id.bt_myview);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent收到ACTION_DOWN事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent收到ACTION_UP事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent走default分支");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LoginActivity的onTouchEvent收到ACTION_DOWN事件");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LoginActivity的onTouchEvent收到ACTION_MOVE事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LoginActivity的onTouchEvent收到ACTION_UP事件");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "LoginActivity的onTouchEvent收到ACTION_CANCEL事件");
                break;
            default:
                Log.d(TAG, "LoginActivity的dispatchTouchEvent走default分支");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
