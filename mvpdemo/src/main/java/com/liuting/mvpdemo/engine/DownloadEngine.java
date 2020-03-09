package com.liuting.mvpdemo.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.liuting.mvpdemo.contract.DownContract;
import com.liuting.mvpdemo.model.ImageBean;
import com.liuting.mvpdemo.presenter.DownloadPresenter;
import com.liuting.mvpdemo.util.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者:admin on 2020/3/8 12:11
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.engine
 * TODO:
 */
public class DownloadEngine implements DownContract.M {
    private DownloadPresenter mDownloadPresenter;

    public DownloadEngine(DownloadPresenter downloadPresenter) {
        this.mDownloadPresenter = downloadPresenter;
    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        //P层让我做这个需求
        new Thread(new DownLoader(imageBean)).start();
    }


    private class DownLoader implements Runnable {
        private ImageBean mImageBean;

        DownLoader(ImageBean imageBean) {
            mImageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(mImageBean.getRequestPath());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constant.SUCCESS, bitmap);
                } else {
                    showUi(Constant.ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constant.ERROR, null);
            }

        }

        private void showUi(int code, Bitmap bitmap) {
            Log.d("mvp","code="+code);
            mImageBean.setBitmap(bitmap);
            mDownloadPresenter.responseDownloaderResult(code == Constant.SUCCESS, mImageBean);
        }
    }
}
