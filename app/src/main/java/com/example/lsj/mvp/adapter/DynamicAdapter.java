package com.example.lsj.mvp.adapter;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvpdemo.R;

import java.util.Date;
import java.util.List;

public class DynamicAdapter extends CommonRecyclerAdapter<DynamicBean> {


    public DynamicAdapter(Context context, List<DynamicBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void bindData(CommonRecyclerHolder holder, DynamicBean data) {
        holder.setText(R.id.dynamic_user_name, data.getUser().getName());

        Glide.with(holder.itemView)
                .load(Api.API+data.getUser().getAvatar())
                .error(R.mipmap.ic_default)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into((ImageView) holder.getView(R.id.dynamic_user_avatar));


        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
        String time;
        Date date = new Date(System.currentTimeMillis());
        long eg = (date.getTime() - data.getTime())/1000;
        if (eg < 30){
            time = "刚刚";
        } else if (eg < 60*60){
            time = eg/60+"分钟前";
        } else if (eg < 2*60*60){
            time = "1小时前";
        }else {
            time = df.format(data.getTime());
        }
        holder.setText(R.id.dynamic_user_time, time);
        holder.setText(R.id.dynamic_context, data.getContext());
    }
}
