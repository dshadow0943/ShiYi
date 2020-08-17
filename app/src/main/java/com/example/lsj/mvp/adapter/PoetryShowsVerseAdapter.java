package com.example.lsj.mvp.adapter;

import android.content.Context;

import com.example.lsj.mvp.R;
import com.example.lsj.mvp.bean.VerseBean;

import java.util.List;

public class PoetryShowsVerseAdapter extends CommonRecyclerAdapter<VerseBean> {

    private Boolean flag = true;

    public PoetryShowsVerseAdapter(Context context, List<VerseBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, VerseBean data) {
        holder.setText(R.id.poetry_verse, data.getText());
    }
}
