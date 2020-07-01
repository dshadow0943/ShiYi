package com.example.lsj.mvpdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationBean;
import com.example.lsj.mvpdemo.utils.DataSet;
import com.example.lsj.mvpdemo.view.activity.PoetryListActivity;

import java.util.List;

public class ClassificationAdapter extends CommonRecyclerAdapter<ClassificationBean> {

    Context context;

    public ClassificationAdapter(Context context, List<ClassificationBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
        this.context = context;
    }

    @Override
    protected void bindData(final CommonRecyclerHolder holder, final ClassificationBean data) {
        holder.setText(R.id.cft_list_name, data.getName());
//        holder.setImageResource(R.id.cft_list_img, data.getImgId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSet.putObject("classification", data);
                context.startActivity(new Intent(context, PoetryListActivity.class));
            }
        });
    }



}
