#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include <string.h>
#include "com_liuting_ndkdemo_jni_JNI.h"
#include "logcat.h"
#include "utils.h"

/*
jobj 谁调用了这个方法就是谁的实例
*/
jstring Java_com_liuting_ndkdemo_jni_JNI_sayHello(JNIEnv *env, jobject jobj, jstring jtr) {
    char *fromJava = _JString2Cstr(env, jtr);
    char *cstr = "add I am from c";
    strcat(fromJava, cstr);
    return (*env)->NewStringUTF(env, fromJava);

}

jint Java_com_liuting_ndkdemo_jni_JNI_add(JNIEnv *env, jobject classz, jint x, jint y) {
    return x + y;
}

/*
 * 给java传进来的数组每个数组加上10
 * */
jintArray
Java_com_liuting_ndkdemo_jni_JNI_increaseArrayEles(JNIEnv *env, jobject clazz, jintArray array) {
    //得到数组长度
    jsize size = (*env)->GetArrayLength(env, array);
    //经测试，即使为JNI_FALSE，两个地址实际上还是不一样的
    jint *intArray = (*env)->GetIntArrayElements(env, array, JNI_FALSE);
    LOGD("%p,%p", array, intArray);
    for (int i = 0; i < size; i++) {
        intArray[i] = intArray[i] + 10;
    }
    //同步
    (*env)->ReleaseIntArrayElements(env, array, intArray, 0);

    return array;
}

jint Java_com_liuting_ndkdemo_jni_JNI_checkPwd(JNIEnv *env, jclass clazz, jstring str) {
    char *origin = "123456";
    char *pwd = _JString2Cstr(env, str);
    int code = strcmp(origin, pwd);
    if (code == 0) {
        return 200;
    } else {
        return 400;
    }
}

//通过code来说明调用的是哪个方法
//code=1 调用的是加法
//code=2 调用的是打印数组
//code=3 调用的是弹出toast
JNIEXPORT jint JNICALL
Java_com_liuting_ndkdemo_jni_JNI_cRunJavaFuncton(JNIEnv *env, jobject clazz, jobject context,
                                                 jint code) {
    char *className = NULL;
    if (code == 1) {
        LOGD("code=%d,进行加法,非静态private方法",code);
        className = "com/liuting/ndkdemo/jni/CUseJavaActivity";
        //1 得到字节码
        jclass clazz1 = (*env)->FindClass(env, className);
        //2得到方法、
        jmethodID pId = (*env)->GetMethodID(env, clazz1, "add", "(II)I");

        //实例化类
        jobject object = (*env)->AllocObject(env, clazz1);

        jint x=6;
        jint y=9;
        //3,调用方法
        jint i = (*env)->CallIntMethod(env, object, pId, x, y);//返回值为int
        LOGD("加法运算结果为i=%d",i);
    } else if (code == 2) {
        LOGD("code=%d,打印一个数组",code);
        className = "com/liuting/ndkdemo/utils/ArrayUtils";
        //1 得到字节码
        jclass clazz1 = (*env)->FindClass(env, className);
        //2得到方法、
        jmethodID pId = (*env)->GetStaticMethodID(env, clazz1, "showIntArray", "([I)V");

        //构造参数
        jsize size = 5;
        jintArray array = (*env)->NewIntArray(env, size);
        jint temp[size];
        for (int i = 0; i < size; i++) {
            temp[i] = (i + 2) * 5;
        }
        (*env)->SetIntArrayRegion(env, array, 0, size, temp);
        //3,调用方法
        (*env)->CallStaticVoidMethod(env, clazz1, pId, array);//返回值为static void
    } else if (code == 3) {
        LOGD("code=%d,弹出一个吐司",code);
        className = "com/liuting/ndkdemo/utils/ToastUtil";
        //1 得到字节码
        jclass clazz1 = (*env)->FindClass(env, className);
        //2得到方法、
        jmethodID pId = (*env)->GetStaticMethodID(env, clazz1, "showToast", "(Landroid/content/Context;Ljava/lang/String;)V");
        jstring string = (*env)->NewStringUTF(env, "哈哈,是jni通过c调用弹出来的");
        //3,调用方法
        (*env)->CallStaticVoidMethod(env, clazz1, pId, context, string);
    } else {
        LOGE("code值传入错误，未找到相关java方法");
    }

}