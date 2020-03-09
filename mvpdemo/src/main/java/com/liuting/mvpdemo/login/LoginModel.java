package com.liuting.mvpdemo.login;

import com.liuting.mvpdemo.base.BaseModel;
import com.liuting.mvpdemo.bean.UserInfo;

/**
 * 作者:admin on 2020/3/8 15:45
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.login
 * TODO:
 */
public class LoginModel extends BaseModel<LoginPresenter,LoginContract.Model> {

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                if(name.equals("123")){
                    p.getContract().responseResult(new UserInfo("中国银行","刘挺"));
                }else{
                    p.getContract().responseResult(null);
                }
            }
        };
    }
}
