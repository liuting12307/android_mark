package com.liuting.mvpdemo.ipinfo;

import com.liuting.mvpdemo.BaseView;
import com.liuting.mvpdemo.model.IpInfo;

/**
 * 作者:admin on 2019/11/17 20:22
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.ipinfo
 * TODO:
 */
public interface IpInfoContract {
    interface Presenter{
        void getIpInfo(String ip);
    }
    interface View extends BaseView<Presenter> {
        void setIpInfo(IpInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
    }
}
