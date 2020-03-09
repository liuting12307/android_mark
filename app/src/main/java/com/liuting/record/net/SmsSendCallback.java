package com.liuting.record.net;

/**
 * 作者:admin on 2019/12/28 23:42
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.net
 * TODO:
 */
public interface SmsSendCallback {
    void onSmsSendSucess(String msg);
    void onSmsSendFailed(String msg,String exception);
}
