package com.liuting.ndkdemo.jni;


import android.content.Context;

public class JNI {
    public  native String sayHello(String string);

    public native int add(int x,int y);

    //用c将每个元素加10
    public native int[] increaseArrayEles(int[] intArray);

    //检查密码是否正确，如果正确返回1，否则返回0
    public native static int checkPwd(String pwd);

    public native void cRunJavaFuncton(Context context,int code);

}
