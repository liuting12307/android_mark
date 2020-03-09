package com.liuting.mvvmdemo.vm;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.liuting.mvvmdemo.databinding.ActivityMainBinding;
import com.liuting.mvvmdemo.model.UserInfo;

/**
 * 作者:admin on 2020/3/9 10:34
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvvmdemo.vm
 * TODO:
 */
public class LoginViewModel {
    public UserInfo userInfo;
    TextView mTextView;
    private ActivityMainBinding mActivityMainBinding;

    public LoginViewModel(ActivityMainBinding viewDataBinding) {
        userInfo=new UserInfo();
        mActivityMainBinding=viewDataBinding;
        //将ViewModel和view进行绑定，通过databinding工具
        viewDataBinding.setLoginViewModel(this);
        initListener();
    }

    private void initListener() {
        mActivityMainBinding.etUsename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //view层接收到用户的输入，改变model层javabean属性
                userInfo.name.set(s.toString());
            }
        });
        mActivityMainBinding.etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userInfo.pwd.set(s.toString());
            }
        });
        mActivityMainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mvvm","name="+userInfo.name.get()+",pwd="+userInfo.pwd.get());
                if(userInfo.name.get().equals("123")&&userInfo.pwd.get().equals("123")){
                    Log.d("mvvm","登录成功");
                }else{
                    Log.d("mvvm","登录失败");
                }
            }
        });
    }

}
