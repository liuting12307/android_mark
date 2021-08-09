package com.liuting.aopdemo.aspect;

import android.util.Log;

import com.liuting.aopdemo.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 作者:admin on 2020/3/8 01:49
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo.aspect
 * TODO:
 */
@Aspect//定义一个切面类
public class ClickBehaviorAspect {

    private final String TAG="BehaviorAspect";


    //1,找到需要处理的切入点
    //execution 以方法执行时作为切点，触发Aspect类
    //* *(..) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.liuting.aopdemo.annotation.ClickBehavior * *(..))")
    public void methodPointCut(){

    }

    //2,对切入点如何处理
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        //获取签名方法
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        //获取类名
        String className = methodSignature.getDeclaringType().getSimpleName();
        Log.e(TAG,"className--"+className);
        //获取方法名
        String methodName = methodSignature.getName();
        Log.e(TAG,"methodName--"+methodName);
        //获取方法的注解值(统计用户的行为)
        String funName = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();
        Log.e(TAG,"funName--"+funName);

        //统计方法的执行时间，统计用户点击某功能行为
        long begin=System.currentTimeMillis();
        Log.e(TAG,"ClickBehavior start");
        Object proceed = joinPoint.proceed();//MainActivity 中切面的方法
        long duration=System.currentTimeMillis()-begin;
        Log.e(TAG,"ClickBehavior end");
        Log.e(TAG,String.format("统计了：%s功能，在%s类的%s方法,用时%d",funName,className,methodName,duration));
        return proceed;
    }
}
