package com.example.lsj.mvpdemo.adapter;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationItem;

import java.util.List;

public class ClassificationItemAdapter extends CommonRecyclerAdapter<ClassificationItem> {

    Context context;
    CommonRecyclerHolder.onClickCommonListener clickCommonListener;

    public ClassificationItemAdapter(Context context, List<ClassificationItem> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
        this.context = context;
        this.clickCommonListener = clickCommonListener;
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, ClassificationItem data) {
        holder.setText(R.id.cft_title, data.getName());

        RecyclerView recyclerView = holder.getRecyclerView(R.id.cft_list);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false));
        ClassificationAdapter classificationAdapter = new ClassificationAdapter(context, data.getList(), R.layout.fragment_classification_l, clickCommonListener);
        recyclerView.setAdapter(classificationAdapter);
    }
}
