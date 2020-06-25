package com.example.lsj.mvpdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class baseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected List<T> tList;
    protected View view;

    public baseAdapter(List<T> tList) {
        this.tList = tList;
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(getViewId(), parent, false);
        return getmVH(view);
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    protected abstract int getViewId();

    protected abstract V getmVH(View view);

}
