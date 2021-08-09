package com.liuting.ndkdemo.jni;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuting.ndkdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者:admin on 2020/1/12 15:48
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.ndkdemo.jni
 * TODO:
 */
public class CUseJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctojava);
        initView();
        initEvents();

    }

    private void initEvents() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    private void initView() {
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                new JNI().cRunJavaFuncton(this,3);
                break;
            case R.id.button2:
                new JNI().cRunJavaFuncton(this,1);
                break;
            case R.id.button3:
                new JNI().cRunJavaFuncton(this,2);
                break;
            default:
                break;
        }
    }


    private int add(int x,int y){
        return x+y;
    }


}
