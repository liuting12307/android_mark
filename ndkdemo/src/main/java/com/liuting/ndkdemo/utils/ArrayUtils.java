package com.liuting.ndkdemo.utils;

import android.util.Log;

/**
 * 作者:admin on 2020/1/12 15:57
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.ndkdemo.utils
 * TODO:
 */
public class ArrayUtils {
    public static void showIntArray(int[] arr){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for(int i=0;i<arr.length;i++){
            if(i==arr.length-1){
                stringBuilder.append(arr[i]).append("]");
            }else{
                stringBuilder.append(arr[i]).append(",");
            }
        }

        Log.d("ArrayUtils",stringBuilder.toString());
    }
}
