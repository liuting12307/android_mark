package com.liuting.mvpdemo.contract;

import com.liuting.mvpdemo.model.ImageBean;

/**
 * 作者:admin on 2020/3/8 11:59
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.contract
 * TODO:
 */
//View层交互，Model层交互共同的需求(契约，合同)
public interface DownContract {
    interface M{
        //P层告诉Model层，需要做什么事情
        void requestDownloader(ImageBean imageBean) throws Exception;
    }

    interface PV{
        //V层告诉P层，需要做什么事情
        void requestDownloader(ImageBean imageBean);

        //P层得到M层的结果返回，再通知V层
        void responseDownloaderResult(boolean isSuccess,ImageBean imageBean);
    }
}
