package com.example.lsj.mvpdemo.adapter;

import android.content.Context;

import com.example.lsj.mvpdemo.R;

import java.util.List;

public class ClassicVerseVAdapter extends CommonRecyclerAdapter<String> {

    List<String> dataList;

    protected ClassicVerseVAdapter(Context context, List<String> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setText(R.id.verse_v, dataList.get(dataList.size()-1-position));
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, String data) {

    }
}
