package com.liuting.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.liuting.mvpdemo.base.BaseView;
import com.liuting.mvpdemo.bean.UserInfo;
import com.liuting.mvpdemo.login.LoginContract;
import com.liuting.mvpdemo.login.LoginPresenter;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:admin on 2020/3/8 19:46
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo
 * TODO:
 */
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    @BindView(R.id.et_usename)
    EditText mNameEt;

    @BindView(R.id.et_pwd)
    EditText mPwdEt;

    @BindView(R.id.bt_login)
    Button mLoginBt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void login(){
        String name=mNameEt.getText().toString();
        String pwd=mPwdEt.getText().toString();
        p.getContract().requestLogin(name,pwd);
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {
                if(userInfo!=null){
                    Log.d("LoginActivity",userInfo.toString());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Log.d("LoginActivity","登录失败");
                }
            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
