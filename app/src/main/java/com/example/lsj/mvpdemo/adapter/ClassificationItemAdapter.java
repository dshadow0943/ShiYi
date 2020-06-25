package com.example.lsj.mvpdemo.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationItem;

import java.util.List;

public class ClassificationItemAdapter extends baseAdapter<ClassificationItem, ClassificationItemAdapter.ViewHolder> {

    public ClassificationItemAdapter(List<ClassificationItem> cfts) {
        super(cfts);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(tList.get(i).getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3, OrientationHelper.HORIZONTAL, false);
        viewHolder.recyclerView.setLayoutManager(gridLayoutManager);
        viewHolder.recyclerView.setAdapter(new ClassificationAdapter(tList.get(i).getCfts()));

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_classification;
    }

    @Override
    protected ViewHolder getmVH(View view) {
        return new ViewHolder(view);
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.cft_title);
            recyclerView = view.findViewById(R.id.cft_list);
        }
    }
}
