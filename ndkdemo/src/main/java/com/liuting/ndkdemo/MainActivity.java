package com.liuting.ndkdemo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.liuting.ndkdemo.jni.CUseJavaActivity;
import com.liuting.ndkdemo.jni.JNI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG=getClass().getSimpleName();

    static {
        System.loadLibrary("jni-test");
    }

    private JNI mJni;
    private Button mAddBt;
    private Button mHelloBt;
    private Button mArrayBt;
    private Button mCheckBt;
    private EditText mPwdET;
    private int[] mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, CUseJavaActivity.class);
                startActivity(intent);
            }
        });
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mAddBt.setOnClickListener(this);
        mHelloBt.setOnClickListener(this);
        mArrayBt.setOnClickListener(this);
        mCheckBt.setOnClickListener(this);
    }

    private void initData() {
        mJni = new JNI();
        mArray = new int[]{1,2,3,4,5};
    }

    private void initView() {
        mAddBt = findViewById(R.id.bt_add);
        mHelloBt = findViewById(R.id.bt_hello);
        mArrayBt = findViewById(R.id.bt_array);
        mCheckBt = findViewById(R.id.bt_checkPwd);
        mPwdET = findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                int result=mJni.add(20,30);
                Log.d(TAG,"加法结果为="+result);
                break;
            case R.id.bt_hello:
                Log.d(TAG,mJni.sayHello("我来自java--"));
                break;
            case R.id.bt_array:
                Log.d(TAG, Arrays.toString(mJni.increaseArrayEles(mArray)));
                break;
            case R.id.bt_checkPwd:
                String pwd=mPwdET.getText().toString();
                if(!TextUtils.isEmpty(pwd)){
                    int code=JNI.checkPwd(pwd);
                    if(code==200){
                        Log.d(TAG,"密码正确");
                    }else{
                        Log.d(TAG,"密码错误");
                    }
                }else{
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


}
