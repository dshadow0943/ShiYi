package com.example.lsj.mvpdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.VerseBean;

import java.util.List;

public class PoetryShowsVerseAdapter extends CommonRecyclerAdapter<VerseBean> {

    public PoetryShowsVerseAdapter(Context context, List<VerseBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position == 0){
            TextView verse = holder.getView(R.id.poetry_verse);
            verse.setTextSize(20);
            verse.setTextColor(Color.BLACK);
        }
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, VerseBean data) {
        holder.setText(R.id.poetry_verse, data.getText());
    }
}
