package com.liuting.mvpdemo.ipinfo;

import com.liuting.mvpdemo.LoadTasksCallBack;
import com.liuting.mvpdemo.model.IpInfo;
import com.liuting.mvpdemo.net.NetTasK;

/**
 * 作者:admin on 2019/11/17 20:29
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.ipinfo
 * TODO:
 */
public class IpInfoPresenter implements IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    private NetTasK<String> mNetTask;
    private IpInfoContract.View addTaskView;

    public IpInfoPresenter(NetTasK<String> netTask, IpInfoContract.View addTaskView) {
        mNetTask = netTask;
        this.addTaskView = addTaskView;
    }

    @Override
    public void getIpInfo(String ip) {
        mNetTask.execute(ip,this);
    }

    @Override
    public void onSuccess(IpInfo ipInfo) {
        addTaskView.setIpInfo(ipInfo);
    }

    @Override
    public void onStart() {
        addTaskView.showLoading();
    }

    @Override
    public void onFailed() {
          addTaskView.showError();
          addTaskView.hideLoading();
    }

    @Override
    public void onFinish() {
        addTaskView.hideLoading();
    }
}
