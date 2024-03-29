package com.example.lsj.mvp.adapter;

import android.content.Context;

import com.example.lsj.mvp.R;
import com.example.lsj.mvp.bean.PoetryWorksBean;

import java.util.List;

public class PoetryWroksAdapter extends CommonRecyclerAdapter<PoetryWorksBean> {


    public PoetryWroksAdapter(Context context, List<PoetryWorksBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, PoetryWorksBean data) {
        holder.setText(R.id.works_title, data.getName());
        holder.setText(R.id.works_dynasty, data.getDynasty());
        holder.setText(R.id.works_author, data.getAuthorName());
        if(data.getVerse() != null){
            holder.setText(R.id.works_verse, data.getVerse());
        }
        holder.setOnClick(holder.itemView);
    }
}
