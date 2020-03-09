package com.liuting.mvpdemo.login;

import com.liuting.mvpdemo.LoginActivity;
import com.liuting.mvpdemo.base.BasePresenter;
import com.liuting.mvpdemo.bean.UserInfo;

/**
 * 作者:admin on 2020/3/8 15:46
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.login
 * TODO:
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel, LoginContract.Presenter> {

    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            //既要旅行view给他的需求，又要分配工作给Model去完成这个请求
            @Override
            public void requestLogin(String name, String pwd) {
                //p层很极端，要不不做事（转发），要么全部做
                try {
                    m.getContract().executeLogin(name,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void responseResult(UserInfo userInfo) {
                //不管谁完成需求，有结果就要告知view层
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModle() {
        return new LoginModel(this);
    }
}
