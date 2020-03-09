package com.liuting.mvvmdemo.model;

import androidx.databinding.ObservableField;

/**
 * 作者:admin on 2020/3/9 10:19
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvvmdemo.model
 * TODO:
 */
public class UserInfo {
    //被观察的属性(根据databinding规范，必须是public修饰符)
    public ObservableField<String> name=new ObservableField<>();

    public ObservableField<String> pwd=new ObservableField<>();
}
