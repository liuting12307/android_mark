package com.liuting.aopdemo.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.liuting.aopdemo.LoginActivity;
import com.liuting.aopdemo.annotation.ClickBehavior;
import com.liuting.aopdemo.utils.Consants;

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
public class LoginCheckAspect {

    private final String TAG="netease   >>";


    //1,找到需要处理的切入点
    //execution 以方法执行时作为切点，触发Aspect类
    //* *(..) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.liuting.aopdemo.annotation.LoginCheck * *(..))")
    public void methodPointCut(){

    }

    //2,对切入点如何处理
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        Context context= (Context) joinPoint.getThis();
        if(Consants.hasLogin){//从sp文件读取状态
            Log.e(TAG,"检测到已登录");
            return joinPoint.proceed();
        }else{
            Log.e(TAG,"检测到未登录");
            Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
            return null;//方法不执行
        }
    }
}
