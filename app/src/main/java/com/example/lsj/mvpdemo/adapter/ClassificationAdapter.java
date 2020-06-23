package com.example.lsj.mvpdemo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationItem;

import java.util.List;

public class ClassificationAdapter extends RecyclerView.Adapter<ClassificationAdapter.ViewHolder> {

    private View view;
    private List<ClassificationItem> cfts;

    public ClassificationAdapter(List<ClassificationItem> cfts) {
        this.cfts = cfts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_classification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.e("TAG", "nameId2: "+viewHolder.name.getText());
    }

    @Override
    public int getItemCount() {
        return cfts.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.cft_name);
        }
    }

}
