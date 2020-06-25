package com.example.lsj.mvpdemo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.bean.ClassificationBean;

import java.util.List;

public class ClassificationAdapter extends baseAdapter<ClassificationBean, ClassificationAdapter.ViewHolder> {

    public ClassificationAdapter(List<ClassificationBean> tList) {
        super(tList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(tList.get(position).getName());
        holder.img.setImageResource(R.drawable.ic_launcher_foreground);
//        holder.img.setImageResource(cfrs.get(position).getImgId());
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_classification_l;
    }

    @Override
    protected ViewHolder getmVH(View view) {
        return new ViewHolder(view);
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
