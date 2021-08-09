package com.liuting.jetpackdemp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 作者:admin on 2020/6/21 10:08
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.jetpackdemp.viewmodel
 * TODO:
 */
public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> useName;
    public MutableLiveData<String> getCurrentName(){
        if(useName==null){
            useName=new MutableLiveData<>();
        }
        return useName;
    }
    public void updateName(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    useName.postValue("haha");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
