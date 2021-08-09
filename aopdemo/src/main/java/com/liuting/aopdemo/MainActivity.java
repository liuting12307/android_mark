package com.liuting.aopdemo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.liuting.aopdemo.annotation.ClickBehavior;
import com.liuting.aopdemo.annotation.LoginCheck;
import com.liuting.aopdemo.utils.Consants;

public class MainActivity extends AppCompatActivity {

    private final String TAG="netease   >>";

    @BindView(R.id.bt_login)
     Button loginBt;

    @BindView(R.id.bt_my_special)
     Button specialBt;

    @BindView(R.id.bt_my_score)
     Button scoreBt;

    @BindView(R.id.bt_setting)
     Button settingBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    //(用户行为统计)
    @OnClick(R.id.bt_login)
    @ClickBehavior("登录")
    public void login() {
        Log.e(TAG,"模拟接口请求...验证通过,登录成功");
        try {
            Thread.sleep(500);
            Consants.hasLogin=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //(用户行为统计)
    @OnClick(R.id.bt_my_special)
    @ClickBehavior("我的专区")
    @LoginCheck
    public void toMySpecial() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"开始跳转到我的专区");
    }

    //(用户行为统计)
    @OnClick(R.id.bt_my_score)
    @LoginCheck
    @ClickBehavior("我的得分")
    public void toMyScore() {
        Log.e(TAG,"开始跳转到我的得分");
    }

    //(用户行为统计)
    @OnClick(R.id.bt_setting)
    @ClickBehavior("我的设置")
    @LoginCheck
    public void toSetting() {
        Log.e(TAG,"开始跳转到我的设置");
    }
}
