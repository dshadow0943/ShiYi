package com.example.lsj.mvp.adapter;

import android.content.Context;

import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvpdemo.R;

import java.util.List;

public class AuthorAdapter extends CommonRecyclerAdapter<AuthorBean> {


    public AuthorAdapter(Context context, List<AuthorBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, AuthorBean data) {
        holder.setText(R.id.author_name, data.getName());
        holder.setText(R.id.author_nsy, "["+data.getDynasty()+"]");
        holder.setOnClick(holder.itemView);
    }

}
