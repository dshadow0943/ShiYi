package com.example.lsj.mvpdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationBean;

import java.util.List;

public class ClassificationAdapter extends RecyclerView.Adapter<ClassificationAdapter.ViewHolder> {

    private List<ClassificationBean> cfrs;
    View view;

    public ClassificationAdapter(List<ClassificationBean> cfrs) {
        this.cfrs = cfrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_classification_l, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(cfrs.get(position).getName());
        holder.img.setImageResource(R.drawable.ic_launcher_foreground);
//        holder.img.setImageResource(cfrs.get(position).getImgId());
    }

    @Override
    public int getItemCount() {
        if (cfrs.size()>15){
            return 15;
        }
        return cfrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name;

        public ViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.cft_list_img);
            name = view.findViewById(R.id.cft_list_name);
        }
    }
}
