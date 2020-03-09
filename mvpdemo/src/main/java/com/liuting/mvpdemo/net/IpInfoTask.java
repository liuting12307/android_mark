package com.liuting.mvpdemo.net;

import com.liuting.mvpdemo.LoadTasksCallBack;
import com.liuting.mvpdemo.model.IpInfo;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 作者:admin on 2019/11/17 20:08
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.net
 * TODO:
 */
public class IpInfoTask implements NetTasK<String> {
    private static IpInfoTask INSTANCE=null;
    private static final String HOST="http://ip.aliyun.com/service/getIpInfo.php";

    private IpInfoTask(){

    }

    public static IpInfoTask getInstance() {
        if(INSTANCE==null){
            INSTANCE=new IpInfoTask();
        }
        return INSTANCE;
    }
    @Override
    public void execute(String ip, LoadTasksCallBack callBack) {
        RequestParams requestParams=new RequestParams();
        requestParams.addFormDataPart("ip",ip);
        HttpRequest.post(HOST,requestParams,new BaseHttpRequestCallback<IpInfo>(){
            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            protected void onSuccess(IpInfo ipInfo) {
                super.onSuccess(ipInfo);
                callBack.onSuccess(ipInfo);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callBack.onFinish();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                callBack.onFailed();
            }
        });
    }
}
