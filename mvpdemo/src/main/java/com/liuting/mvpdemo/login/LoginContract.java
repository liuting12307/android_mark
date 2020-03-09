package com.liuting.mvpdemo.login;

import com.liuting.mvpdemo.bean.BaseEntity;

/**
 * 作者:admin on 2020/3/8 15:04
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.login
 * TODO:
 */
//将model层 view层  Presenter层协商的共同业务 ，封装成接口
public interface LoginContract {
    interface Model{
        //Model的子类完成方法的具体实现
        void executeLogin(String name,String pwd) throws Exception;
    }
    interface View<T extends BaseEntity>{
        //真实的项目，请求结果往往是javabean
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity>{
        //登录请求（接受到view层指令，可以自己做，也可以让model层执行）
        void requestLogin(String name,String pwd);

        //结果响应(接受到model层处理的结果，通知view层刷新)
        void responseResult(T t);
    }
}
