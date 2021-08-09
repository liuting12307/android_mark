package com.liuting.ndkdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者:admin on 2020/1/12 15:54
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.ndkdemo.utils
 * TODO:
 */
public class ToastUtil {
    private static Toast mToast;
    public static void showToast(Context context,String msg){
        if(mToast==null){
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
