package com.liuting.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.liuting.mvpdemo.contract.DownContract;
import com.liuting.mvpdemo.ipinfo.IpInfoContract;
import com.liuting.mvpdemo.ipinfo.IpInfoPresenter;
import com.liuting.mvpdemo.model.ImageBean;
import com.liuting.mvpdemo.model.IpInfo;
import com.liuting.mvpdemo.net.IpInfoTask;
import com.liuting.mvpdemo.net.NetTasK;
import com.liuting.mvpdemo.presenter.DownloadPresenter;
import com.liuting.mvpdemo.util.Constant;

public class MainActivity extends AppCompatActivity implements IpInfoContract.View, DownContract.PV {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.bt_ipInfo)
    Button mButton;

    @BindView(R.id.bt_setIma)
    Button mImageBt;

    @BindView(R.id.image)
    ImageView mImageView;

    private IpInfoPresenter mIpInfoPresenter;
    private NetTasK<String> mNetTasK;
    private ProgressDialog mProgressDialog;

    private DownloadPresenter mDownloadPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNetTasK = IpInfoTask.getInstance();
        mIpInfoPresenter = new IpInfoPresenter(mNetTasK, this);
        mDownloadPresenter = new DownloadPresenter(this);
        mProgressDialog = new ProgressDialog(this);
    }

    @OnClick(R.id.bt_ipInfo)
    public void getData() {
        mIpInfoPresenter.getIpInfo("39.155.184.147");
    }

    @OnClick(R.id.bt_setIma)
    public void setBitmap() {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(Constant.imagePath);
        requestDownloader(imageBean);
    }

    @Override
    public void setIpInfo(IpInfo ipInfo) {
        if (ipInfo != null) {
            Log.d(TAG, ipInfo.toString());
        }

    }

    @Override
    public void showLoading() {
        mProgressDialog.setTitle("获取数据中...");
    }

    @Override
    public void hideLoading() {
        mProgressDialog.cancel();
    }

    @Override
    public void showError() {
        Log.d(TAG, "网络错误");
        mProgressDialog.cancel();
    }

    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {

    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        if(mDownloadPresenter!=null){
            mDownloadPresenter.requestDownloader(imageBean);
        }
    }

    @Override
    public void responseDownloaderResult(boolean isSuccess, ImageBean imageBean) {
        Log.e("mvp","下载结果=="+isSuccess);
        if(isSuccess && imageBean.getBitmap()!=null){
            mImageView.setImageBitmap(imageBean.getBitmap());
        }
    }
}
