package com.liuting.mvpdemo.presenter;

import com.liuting.mvpdemo.MainActivity;
import com.liuting.mvpdemo.contract.DownContract;
import com.liuting.mvpdemo.engine.DownloadEngine;
import com.liuting.mvpdemo.model.ImageBean;

/**
 * 作者:admin on 2020/3/8 12:08
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.presenter
 * TODO:
 */
public class DownloadPresenter implements DownContract.PV {
    private MainActivity view;
    private DownloadEngine model;//下载的模型

    public DownloadPresenter(MainActivity view) {
        this.view = view;
        model=new DownloadEngine(this);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        //接受到View层的指令，去完成某个需求（可以自己完成，也可以叫人完成）
        try {
            model.requestDownloader(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void responseDownloaderResult(boolean isSuccess, ImageBean imageBean) {

        //将完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.responseDownloaderResult(isSuccess,imageBean);
            }
        });
    }
}
