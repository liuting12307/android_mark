package com.liuting.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者:admin on 2020/3/29 00:02
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.binderdemo
 * TODO:
 * 1.Binder机制无法跨进程传输超过1M的数据
 * 2.匿名共享内存并没有大小的限制，适合跨进程传输较大的数据
 * 3.匿名共享内存需要先通过Binder传递共享内存的文件句柄
 */
public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("RemoteService","远程服务启动");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if(code==1){
                String str="hello liuting!";
                byte[] bytes = str.getBytes();
                //创建匿名共享内存
                try {
                    MemoryFile mf=new MemoryFile("memfile",bytes.length);
                    //写入字符数据
                    mf.writeBytes(bytes,0,0,bytes.length);
                    Method method = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
                    //通过反射获得文件句柄
                    FileDescriptor fd = (FileDescriptor) method.invoke(mf);
                    ParcelFileDescriptor pfd = ParcelFileDescriptor.dup(fd);
                    //将文件句柄写到binder调用的返回值中
                    reply.writeFileDescriptor(fd);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
