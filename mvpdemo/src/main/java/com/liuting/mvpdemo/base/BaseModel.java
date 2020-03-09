package com.liuting.mvpdemo.base;

/**
 * 作者:admin on 2020/3/8 15:03
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.base
 * TODO:
 */
//需要接受到P层交给他的
public abstract class BaseModel<P extends BasePresenter,CONTRACT> {
    protected P p;

    //业务介绍，通过Presenter调用契约方法 void responseResult(T t)
    public BaseModel(P p){
        this.p=p;
    }
    public abstract CONTRACT getContract();
}
