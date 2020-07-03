package com.example.lsj.mvpdemo.view.activity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.adapter.PoetryShowsVerseAdapter;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.bean.AppreciationBean;
import com.example.lsj.mvpdemo.bean.CommentBean;
import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.contract.PoetryShowContract;
import com.example.lsj.mvpdemo.presenter.PoetryShowPresenter;
import com.example.lsj.mvpdemo.utils.DataSet;

import java.util.ArrayList;
import java.util.List;

public class PoetryShowActivity extends BaseActivity<PoetryShowPresenter> implements PoetryShowContract.View, CommonRecyclerHolder.onClickCommonListener, View.OnClickListener {

    private PoetryWorksBean poetryWorks;
    private PoetryBean poetry;
    private TextView commentText;
    private TextView translationText;
    private TextView appreciationText;
    private TextView textText;
    private TextView authorSummaryText;
    private RecyclerView classicRecyclerView;

    List<TextView> viewList = new ArrayList<>();


    @Override
    protected PoetryShowPresenter createPresenter() {
        return new PoetryShowPresenter();
    }

    @Override
    protected void bindinLayout() {

        commentText = findViewById(R.id.poetry_show_comment);
        translationText = findViewById(R.id.poetry_show_translation);
        appreciationText = findViewById(R.id.poetry_show_appreciation);
        textText = findViewById(R.id.poetry_show_text);
        authorSummaryText = findViewById(R.id.poetry_author_summary);
        classicRecyclerView = findViewById(R.id.poetry_classics);

        commentText.setOnClickListener(this);
        translationText.setOnClickListener(this);
        appreciationText.setOnClickListener(this);

        viewList.add(commentText);
        viewList.add(translationText);
        viewList.add(appreciationText);

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_show;
    }

    @Override
    protected void init() {
        poetryWorks = (PoetryWorksBean) DataSet.getObjectData("poetryWorks");
        if (poetryWorks == null){
            return;
        }
        mPresenter.getPoetryItem(poetryWorks.getId());
    }

    @Override
    public void showWorksSuccess(PoetryBean bean) {
        Log.e("TAG", "showWorksSuccess: " + bean.toString());
        poetry = bean;
        config();
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

    private void config(){

        List<VerseBean> verses = new ArrayList<>();
        verses.add(new VerseBean(poetry.getName()));
        verses.add(new VerseBean(poetry.getDynasty()+" "+poetry.getAuthorName()));
        verses.addAll(poetry.getVerses());

        RecyclerView recyclerViewVerse = findViewById(R.id.poetry_verse);
        recyclerViewVerse.setLayoutManager(new LinearLayoutManager(this));
        PoetryShowsVerseAdapter verseAdapter = new PoetryShowsVerseAdapter(this, verses, R.layout.activity_poetry_show_verse, this);
        recyclerViewVerse.setAdapter(verseAdapter);

        changeText(0);

    }

    private String initText(int position){
        String text = "";
        if (position == 0){
            for (CommentBean c:poetry.getComments()){
                text = text + c.getField()+": "+c.getText()+"\n";
            }
        } else if (position == 1){
            for (VerseBean v : poetry.getVerses()){
                text = text + v.getTranslation() + "\n";
            }
        } else if (position == 2){
            for (AppreciationBean a : poetry.getAppreciations()){
                text = text + a.getText() + "\n";
            }
        }

        return text;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.poetry_show_comment:
                changeText(0);
                break;

            case R.id.poetry_show_translation:
                changeText(1);
                break;

            case R.id.poetry_show_appreciation:
//                changeText(2);
                break;
        }

    }

    private void changeText(int position){

        for (int i = 0; i < viewList.size(); i++) {
            if (i == position){
                viewList.get(i).setTextColor(Color.RED);
            } else {
                viewList.get(i).setTextColor(Color.DKGRAY);
            }
        }

        textText.setText(initText(position));

    }

}
