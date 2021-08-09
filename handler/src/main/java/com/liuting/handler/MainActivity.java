package com.liuting.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler mHandler ;
    volatile  boolean flag=true;
    final  static String TAG = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Message message = Message.obtain();
        message.obj = "123";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Log.d(TAG,"处理消息:"+msg.obj);
                        flag=false;
                        Log.d(TAG,"处理:"+flag);
                    }
                };
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        while (flag){
                            Log.d(TAG,"zhi:"+flag);
                            Log.d(TAG,"子线程打印数据");
                        }
                    }
                },200);
                Log.d(TAG,"子线程loop");
                Looper.loop();

            }
        }).start();
        try {
            Thread.sleep(2000);
            mHandler.sendMessage(message);
            Log.d(TAG,"主线程发完消息了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
