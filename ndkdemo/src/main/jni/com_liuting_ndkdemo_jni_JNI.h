/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_liuting_ndkdemo_jni_JNI */

#ifndef _Included_com_liuting_ndkdemo_jni_JNI
#define _Included_com_liuting_ndkdemo_jni_JNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_liuting_ndkdemo_jni_JNI
 * Method:    sayHello
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_liuting_ndkdemo_jni_JNI_sayHello
  (JNIEnv *, jobject,jstring);

/*
 * Class:     com_liuting_ndkdemo_jni_JNI
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_liuting_ndkdemo_jni_JNI_add
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_liuting_ndkdemo_jni_JNI
 * Method:    increaseArrayEles
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_liuting_ndkdemo_jni_JNI_increaseArrayEles
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_liuting_ndkdemo_jni_JNI
 * Method:    checkPwd
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_liuting_ndkdemo_jni_JNI_checkPwd
  (JNIEnv *, jclass, jstring);



JNIEXPORT jint JNICALL Java_com_liuting_ndkdemo_jni_JNI_cRunJavaFuncton
        (JNIEnv *, jobject, jobject ,jint);

#ifdef __cplusplus
}
#endif
#endif
