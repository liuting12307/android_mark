package com.liuting.record.net;

import android.text.TextUtils;
import android.util.Log;

import com.liuting.record.utils.Contants;
import com.liuting.record.utils.MD5Utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者:admin on 2019/12/28 23:23
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.net
 * TODO:
 */
public class OkHttpUtils {


    private String TAG = getClass().getSimpleName();

    private static OkHttpClient okHttpClient;

    private static OkHttpUtils instance;

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance=new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    private OkHttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//10秒连接超时
                .writeTimeout(10, TimeUnit.SECONDS)//10m秒写入超时
                .readTimeout(10, TimeUnit.SECONDS)//10秒读取超时
                .build();
    }

    public void postMsg(final String msg, final SmsSendCallback smsSendCallback) {
        Log.d(TAG, "原始数据：" + msg);
        final String msgf = msg;

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("timestamp", new Date().getTime() / 1000 + "");
        hashMap.put("message", msg);
        RequestBody requestBody = new FormBody.Builder()
                .add("timestamp", new Date().getTime() / 1000 + "")
                .add("sign", getSign(hashMap))
                .add("message", msg)
                .build();

        final Request request = new Request.Builder()
                .url(Contants.HTTP_URL + "/message/accept")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure：" + e.getMessage());
                smsSendCallback.onSmsSendFailed(msg,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                Log.d(TAG, "Code：" + response.code() + responseStr);
                if(response.code()==200){
                    smsSendCallback.onSmsSendSucess(msg);
                }else {
                    smsSendCallback.onSmsSendFailed(msg,response.message());
                }
            }
        });
    }

    private String getSign(HashMap<String, Object> map) {
        TreeMap<String, Object> tMap = new TreeMap<>();
        if (map != null && !map.isEmpty()) {
            StringBuffer params = new StringBuffer();
            for (Map.Entry<String, Object> ent : map.entrySet()) {
                if (!TextUtils.isEmpty(ent.getKey()) && !TextUtils.isEmpty(ent.getValue().toString())) {
                    try {
                        tMap.put(ent.getKey(), URLEncoder.encode(ent.getValue().toString(), "utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (Map.Entry<String, Object> ent : tMap.entrySet()) {
                params.append(ent.getKey()).append("=").append(ent.getValue().toString()).append("&");
            }
            params.append("key=").append(Contants.KEY);
            String sign = MD5Utils.md5(params.toString().toLowerCase()).toLowerCase();
            Log.d(TAG, "排序后的字符串:" + params.toString());
            Log.d(TAG, "小写后的字符串:" + params.toString().toLowerCase());
            Log.d(TAG, "签名数据:" + sign);
            return sign;

        }
        return "";
    }
}
