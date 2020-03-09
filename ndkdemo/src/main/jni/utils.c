//
// Created by admin on 2020/1/11.
//
#include "utils.h"
#include <jni.h>
#include <malloc.h>
#include <string.h>

char* _JString2Cstr(JNIEnv* env, jstring jstr){
    char* rtn=NULL;
    jclass clsstring=(*env)->FindClass(env,"java/lang/String");
    jstring strencode=(*env)->NewStringUTF(env,"UTF-8");
    jmethodID  mid=(*env)->GetMethodID(env,clsstring,"getBytes","(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid, strencode); // String .getByte("UTF-8");
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if(alen > 0) {
        rtn = (char*)malloc(alen+1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen]=0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba,0);
    return rtn;

}