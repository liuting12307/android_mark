package com.liuting.aopdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者:admin on 2020/3/8 01:45
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo.annotation
 * TODO:
 */
@Target(ElementType.METHOD)//目标作用在方法之上
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickBehavior {
    String value();
}
