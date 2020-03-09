package com.liuting.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 作者:admin on 2020/3/8 15:25
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.base
 * TODO:view层基类
 */
public abstract class BaseView<P extends BasePresenter,CONTRACT> extends Activity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //弱引用
        p=getPresenter();
        //绑定
        p.bindView(this);
    }

    //让P层做什么需求
    public abstract CONTRACT getContract();

    //从子类中获取
    public abstract P getPresenter();

    //若果Presenter层出现了异常，需要告知view层
    public void error(Exception e){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unBindView();
    }
}
