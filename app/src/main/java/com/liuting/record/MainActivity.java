package com.liuting.record;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liuting.record.adapter.SmsSendAdapter;
import com.liuting.record.bean.SmsSendItemBean;
import com.liuting.record.net.SmsSendCallback;
import com.liuting.record.obsever.SmsObserver;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Uri SMS_INBOX = Uri.parse("content://sms");

    private String[] storagePerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private String[] smsPerm={Manifest.permission.READ_SMS};
    private static final int PERMISSON_READ_SMS = 0X05;
    private static final int PERMISSON_STORAGE = 0X04;
    private static final int PERMISSON_SETTING_RESULT_CODE = 0X08;
    private Button mReadSmsBt;
    private TextView mCountTV;
    private RecyclerView mReacodRecyclerView;
    private SmsObserver smsObserver;
    private int count;
    private ArrayList<SmsSendItemBean> mItemBeans;

    private boolean hasRegisterSms=false;

    public Handler smsHandler=new Handler() {
        //这里可以进行回调的操作
        //TODO

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
    private SmsSendAdapter mSmsSendAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mReadSmsBt = findViewById(R.id.bt_read_sms);
        mCountTV = findViewById(R.id.tv_count);
        mReacodRecyclerView = findViewById(R.id.rv_sms_cord);
    }

    private void initData() {
        checkStoragePermission();
        smsObserver = new SmsObserver(this, smsHandler);
        mItemBeans=new ArrayList<>();
        mReacodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSmsSendAdapter = new SmsSendAdapter(this,mItemBeans);
        mReacodRecyclerView.setAdapter(mSmsSendAdapter);

    }

    private void checkStoragePermission() {
        if (!EasyPermissions.hasPermissions(MainActivity.this, storagePerms)) {
            Logger.d("弹出权限申请框");
            EasyPermissions.requestPermissions(MainActivity.this,getString(R.string.permission_storage_request_tips), PERMISSON_STORAGE, storagePerms);
        }
    }

    private void initListener() {
        smsObserver.setSmsSendCallback(new SmsSendCallback() {
            @Override
            public void onSmsSendSucess(String msg) {
                try {
                    JSONObject jsonObject = new JSONObject(msg);
                    SmsSendItemBean itemBean=new SmsSendItemBean();
                    itemBean.setTime(jsonObject.getString("date"));
                    itemBean.setNum(jsonObject.getString("phone"));
                    itemBean.setStatus("成功");
                    mItemBeans.add(itemBean);
                    count++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCountTV.setText("转发短信的数目："+count);
                            mSmsSendAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSmsSendFailed(String msg,String exception) {

                try {
                    JSONObject jsonObject = new JSONObject(msg);
                    SmsSendItemBean itemBean=new SmsSendItemBean();
                    itemBean.setTime(jsonObject.getString("date"));
                    itemBean.setNum(jsonObject.getString("phone"));
                    itemBean.setStatus("失败");
                    itemBean.setFailedDesc(exception);
                    mItemBeans.add(itemBean);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSmsSendAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        mReadSmsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(isMIUI()){
                       AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
                       int checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_READ_SMS, Process.myUid(), getPackageName());
                       Logger.d("checkOp="+checkOp);
                       if (checkOp != AppOpsManager.MODE_ALLOWED) {
                           tipsToSettingPermissionActivity();
                       } else {
                           //获取权限后的操作
                           if(!hasRegisterSms){
                               hasRegisterSms=true;
                               getContentResolver().registerContentObserver(SMS_INBOX, true,
                                       smsObserver);
                           }
                       }
                   }else{
                       if (EasyPermissions.hasPermissions(MainActivity.this, smsPerm)) {
                           Logger.d("已经拥有读取短信权限");
                           if(!hasRegisterSms){
                               hasRegisterSms=true;
                               getContentResolver().registerContentObserver(SMS_INBOX, true,
                                       smsObserver);
                           }
                       }else{
                           EasyPermissions.requestPermissions(MainActivity.this,getString(R.string.permission_readsms_request_tips), PERMISSON_READ_SMS, smsPerm);
                       }
                   }
                   if(hasRegisterSms){
                       mReadSmsBt.setText("短信监听已经开启");
                   }
                }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PERMISSON_READ_SMS:
                Logger.d("已经申请到读取短信权限");
                if(!hasRegisterSms){
                    hasRegisterSms=true;
                    getContentResolver().registerContentObserver(SMS_INBOX, true,
                            smsObserver);
                }
                break;
            case PERMISSON_STORAGE:
                Logger.d("已经申请到sd卡读取权限");
                break;
            default:
                break;
        }
    }

    private void tipsToSettingPermissionActivity() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog dialog=builder.setMessage("该功能需要申请读取短信权限，请手动开启").
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toSetingPermissionActivity();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case PERMISSON_READ_SMS:
                Logger.d("用户拒绝了申请读取短信权限要求");
                if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                    new AppSettingsDialog.Builder(this).build().show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Logger.d("用户从权限设置界面返回了");
        }else if(requestCode==PERMISSON_SETTING_RESULT_CODE){
            Logger.d("用户赋予了相应权限");
            if(!hasRegisterSms){
                hasRegisterSms=true;
                getContentResolver().registerContentObserver(SMS_INBOX, true,
                        smsObserver);
            }
        }
    }

    public boolean isMIUI() {
        String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
        String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
        String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
        String device = Build.MANUFACTURER;
        Logger.d("Build.MANUFACTURER = " + device);
        if (device.equals("Xiaomi")) {
            Logger.d("this is a xiaomi device");
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } else {
            return false;
        }
    }

    private void toSetingPermissionActivity(){
        Intent miuiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        miuiIntent.putExtra("extra_pkgname",getPackageName());
        //检测是否有能接受该Intent的Activity存在
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(miuiIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfos.size() > 0) {
            startActivityForResult(miuiIntent, PERMISSON_SETTING_RESULT_CODE);
        }
    }

}
