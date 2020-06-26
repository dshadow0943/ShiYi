package com.example.lsj.mvpdemo.adapter;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用ViewHolder
 */
public class CommonRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public interface onClickCommonListener {

        void onClickListener(int position);

        void onLongClickListener(int position);

    }

    private onClickCommonListener clickCommonListener;

    //用来存放View以减少findViewById的次数
    private SparseArray<View> viewSparseArray;

    public CommonRecyclerHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickCommonListener(onClickCommonListener clickCommonListener) {
        this.clickCommonListener = clickCommonListener;
    }

    @Override
    public void onClick(View view) {
        if (clickCommonListener != null) {
            clickCommonListener.onClickListener(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (clickCommonListener != null) {
            clickCommonListener.onLongClickListener(getAdapterPosition());
        }
        return true;
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    private  <T extends View> T getView(int viewId) {
        // 先从缓存中找，找到的话则直接返回
        // 如果找不到则findViewById，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerView getRecyclerView(int viewId){
        return getView(viewId);
    }

    public CommonRecyclerHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonRecyclerHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CommonRecyclerHolder setImageResource(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public CommonRecyclerHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }
}
