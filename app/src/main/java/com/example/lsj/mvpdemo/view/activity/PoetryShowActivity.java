package com.example.lsj.mvpdemo.view.activity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.adapter.PoetryShowsVerseAdapter;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.contract.PoetryShowContract;
import com.example.lsj.mvpdemo.presenter.PoetryShowPresenter;
import com.example.lsj.mvpdemo.utils.DataSet;

import java.util.ArrayList;
import java.util.List;

public class PoetryShowActivity extends BaseActivity<PoetryShowPresenter> implements PoetryShowContract.View, CommonRecyclerHolder.onClickCommonListener {

    private PoetryWorksBean poetryWorksBean;

    @Override
    protected PoetryShowPresenter createPresenter() {
        return new PoetryShowPresenter();
    }

    @Override
    protected void bindinLayout() {

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_show;
    }

    @Override
    protected void init() {
        poetryWorksBean = (PoetryWorksBean) DataSet.getObjectData("poetryWorks");
        if (poetryWorksBean == null){
            return;
        }
        mPresenter.getPoetryItem(poetryWorksBean.getId());
    }

    @Override
    public void showWorksSuccess(PoetryBean beans) {
        Log.e("TAG", "showWorksSuccess: " + beans.toString());
        List<VerseBean> list = new ArrayList<>();
        list.add(new VerseBean(beans.getName()));
        list.add(new VerseBean(beans.getDynasty()+" "+beans.getAuthorName()));
        list.addAll(beans.getVerses());

        RecyclerView recyclerViewVerse = findViewById(R.id.poetry_verse);
        recyclerViewVerse.setLayoutManager(new LinearLayoutManager(this));
        PoetryShowsVerseAdapter verseAdapter = new PoetryShowsVerseAdapter(this, list, R.layout.activity_poetry_show_verse, this);
        recyclerViewVerse.setAdapter(verseAdapter);
    }

    @Override
    public void showWorksFail() {

    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongClickListener(View view, int position) {

    }
}
