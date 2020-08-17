package com.example.lsj.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.bean.ClassificationBean;
import com.example.lsj.mvp.view.fragment.ClassificationFragment;
import com.example.lsj.mvp.R;

import java.util.List;

public class ClassificationAdapter extends CommonRecyclerAdapter<ClassificationBean> {

    ClassificationFragment fragment;

    public ClassificationAdapter(ClassificationFragment fragment, List<ClassificationBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(fragment.getContext(), dataList, layoutId, clickCommonListener);
        this.fragment = fragment;
    }

    @Override
    protected void bindData(final CommonRecyclerHolder holder, final ClassificationBean data) {
        holder.setText(R.id.cft_list_name, data.getName());
        ImageView imageView = (ImageView) holder.getView(R.id.cft_list_img);

        Glide.with(holder.itemView)
                .load(Api.API+data.getImgPath())
                .error(R.mipmap.ic_default)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);

        WindowManager wm = (WindowManager) fragment.getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = (width-40)/4-100;
        lp.height = (width-40)/4-100;
        imageView.setLayoutParams(lp);

        ViewGroup.LayoutParams lp2 = holder.itemView.getLayoutParams();
        lp2.width = (width-40-5*12)/4;
        lp2.height = (width-40-5*12)/4+30;
        holder.itemView.setLayoutParams(lp2);

//        holder.setImageResource(R.id.cft_list_img, data.getImgId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.sendData(data);
            }
        });
    }



}
