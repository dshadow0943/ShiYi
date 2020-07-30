package com.example.lsj.mvp.adapter;

import android.content.Context;
import android.view.WindowManager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.view.fragment.ClassificationFragment;
import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvp.bean.ClassificationItem;

import java.util.List;

public class ClassificationItemAdapter extends CommonRecyclerAdapter<ClassificationItem> {

    ClassificationFragment fragment;
    CommonRecyclerHolder.onClickCommonListener clickCommonListener;

    public ClassificationItemAdapter(ClassificationFragment fragment, List<ClassificationItem> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(fragment.getContext(), dataList, layoutId, clickCommonListener);
        this.fragment = fragment;
        this.clickCommonListener = clickCommonListener;
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, ClassificationItem data) {
        holder.setText(R.id.cft_title, data.getName());
        holder.setOnClick(R.id.cft_more);

        RecyclerView recyclerView = holder.getRecyclerView(R.id.cft_list);

        WindowManager wm = (WindowManager) fragment.getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
//        ViewGroup.LayoutParams lp = recyclerView.getLayoutParams();
//        lp.width = (width-40)/4;
//        lp.height = (width-40)/4;
//        recyclerView.setLayoutParams(lp);


        if (data.getList().size()>=12){
            recyclerView.setLayoutManager(new GridLayoutManager(fragment.getContext(), 3, RecyclerView.HORIZONTAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(fragment.getContext(), 4, RecyclerView.VERTICAL, false));
        }

        ClassificationAdapter classificationAdapter = new ClassificationAdapter(fragment, data.getList(), R.layout.fragment_classification_l, clickCommonListener);
        recyclerView.setAdapter(classificationAdapter);
    }
}
