package com.example.lsj.mvp.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvp.bean.VerseBean;

import java.util.Arrays;
import java.util.List;

public class ClassicVerseAdapter extends CommonRecyclerAdapter<VerseBean> {

    Context context;
    CommonRecyclerHolder.onClickCommonListener clickCommonListener;

   public ClassicVerseAdapter(Context context, List<VerseBean> dataList, int layoutId, CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        super(context, dataList, layoutId, clickCommonListener);
        this.clickCommonListener = clickCommonListener;
        this.context = context;
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, VerseBean data) {
        holder.setText(R.id.verse_show_author, data.getAuthorName());
        String str = data.getText().replace('。', '，');
        List<String> list = Arrays.asList(str.split("，"));

        RecyclerView recyclerView = holder.getView(R.id.verse_show_text);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        ClassicVerseVAdapter vAdapter = new ClassicVerseVAdapter(context, list, R.layout.fragment_classic_verse_v, clickCommonListener);
        recyclerView.setAdapter(vAdapter);

    }
}
