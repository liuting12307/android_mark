package com.liuting.record.exception;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Date;

import androidx.annotation.NonNull;

/**
 * 作者:admin on 2019/8/31 15:34
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.exception
 * TODO:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mCrashHandler=null;
    private Context mContext;
    public static CrashHandler getInstance(){
        if(mCrashHandler==null){
            mCrashHandler=new CrashHandler();
        }
        return mCrashHandler;
    }
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        try {
            StringBuffer sb = new StringBuffer();

            //异常发生时间
            long time = System.currentTimeMillis();
            Date date = new Date(time);
            String timeStr = date.toGMTString();
            sb.append(timeStr);
            sb.append("\n");

            //用户使用的手机型号等信息
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                String values = field.get(null).toString();
                sb.append(name + " : " + values);
                sb.append("\n");
            }

            //发生异常的具体情况
            StringWriter sw = new StringWriter();
            PrintWriter write = new PrintWriter(sw);
            throwable.printStackTrace(write);
            String errorlog = sw.toString();
            String dir= Environment.getExternalStorageDirectory()+"/"+mContext.getPackageName();
            File dirCrash=new File(dir);
            if(!dirCrash.exists()){
                dirCrash.mkdirs();
            }
            File log = new File(dirCrash,"crash.txt");

            FileOutputStream fos;

            fos = new FileOutputStream(log);
            fos.write(sb.toString().getBytes());
            fos.write(errorlog.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 绕过生命周期的顺序,强制关闭.
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void init(Context context) {
        mContext=context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
