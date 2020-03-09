package com.liuting.record.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuting.record.R;
import com.liuting.record.bean.SmsSendItemBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者:admin on 2019/12/29 23:30
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.adapter
 * TODO:
 */
public class SmsSendAdapter extends RecyclerView.Adapter<SmsSendHolder> {
    private Context mContext;
    private ArrayList<SmsSendItemBean> mItemBeans;

    public SmsSendAdapter(Context context, ArrayList<SmsSendItemBean> itemBeans) {
        mContext = context;
        mItemBeans = itemBeans;
    }

    @NonNull
    @Override
    public SmsSendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_sms_history,parent,false);
        return new SmsSendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsSendHolder holder, int position) {
        SmsSendItemBean smsSendItemBean=mItemBeans.get(position);
        holder.mNumTV.setText(smsSendItemBean.getNum());
        holder.mTimeTV.setText(smsSendItemBean.getTime());
        holder.mStatusTV.setText(smsSendItemBean.getStatus());
        if(smsSendItemBean.getStatus().equals("成功")){
            holder.mFailedTV.setVisibility(View.INVISIBLE);
        }else{
            holder.mFailedTV.setVisibility(View.VISIBLE);
            holder.mFailedTV.setText(smsSendItemBean.getFailedDesc());
        }

    }

    @Override
    public int getItemCount() {
        return mItemBeans==null?0:mItemBeans.size();
    }
}

    class SmsSendHolder extends RecyclerView.ViewHolder {

         TextView mNumTV;
         TextView mStatusTV;
         TextView mTimeTV;
         TextView mFailedTV;

        public SmsSendHolder(@NonNull View itemView) {
            super(itemView);
            mNumTV = itemView.findViewById(R.id.tv_num);
            mStatusTV = itemView.findViewById(R.id.tv_status);
            mTimeTV = itemView.findViewById(R.id.tv_time);
            mFailedTV = itemView.findViewById(R.id.tv_message_failed);
        }
    }
