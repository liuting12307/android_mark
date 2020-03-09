package com.liuting.mvpdemo.base;

import java.lang.ref.WeakReference;

/**
 * 作者:admin on 2020/3/8 15:19
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.base
 * TODO: Presenter基类
 */
public abstract class BasePresenter<V extends BaseView,M extends BaseModel,CONTRACT> {
    protected M m;
    private WeakReference<V> mVWeakReference;

    public BasePresenter() {
        m=getModle();
    }

    //获取子类具体契约(Model层和View层协商的共同业务)
    public abstract CONTRACT getContract();

    public void bindView(V v){
       mVWeakReference=new WeakReference<>(v) ;
    }

    //获取View
    public V getView(){
        if(mVWeakReference!=null) {
            return mVWeakReference.get();
        }
        return null;
    }

    public abstract M getModle();

    public void unBindView() {
        if(mVWeakReference!=null){
            mVWeakReference.clear();
            mVWeakReference.clear();
            System.gc();
        }
    }
}
