package com.example.lsj.mvp.adapter;

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
    public int getItemCount() {
        if (dataList.size() > 6){
            return 6;
        }
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String str = dataList.get(getItemCount()-1-position).replaceAll(" {4}", "");

        if (position > 5){
            return;
        }

        if ((getItemCount()-1-position)%2 == 1){
            str = "  丨丨"+str;
        }
        holder.setText(R.id.verse_v, str);
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, String data) {

    }
}
