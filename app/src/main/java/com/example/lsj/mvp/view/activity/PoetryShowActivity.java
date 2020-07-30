package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.PoetryShowsVerseAdapter;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.AppreciationBean;
import com.example.lsj.mvp.bean.CommentBean;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.bean.VerseBean;
import com.example.lsj.mvp.contract.PoetryShowContract;
import com.example.lsj.mvp.presenter.PoetryShowPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.utils.Jump;
import com.example.lsj.mvp.utils.SpaceItemDecorationUtil;
import com.example.lsj.mvpdemo.R;

import java.util.ArrayList;
import java.util.List;

public class PoetryShowActivity extends BaseActivity<PoetryShowPresenter> implements PoetryShowContract.View, CommonRecyclerHolder.onClickCommonListener, View.OnClickListener {

    private PoetryWorksBean poetryWorks;
    RecyclerView recyclerViewVerse;
    private RecyclerView classicRecyclerView;
    private PoetryBean poetry;
    private TextView commentText;
    private TextView translationText;
    private TextView appreciationText;
    private TextView textText;
    private TextView authorSummaryText;
    private TextView titleText;
    private TextView authorText;
    private TextView find;
    private TextView back;
    private SearchView findBox;

    private String findText;

    List<TextView> viewList = new ArrayList<>();


    @Override
    protected PoetryShowPresenter createPresenter() {
        return new PoetryShowPresenter();
    }

    @Override
    protected void bindinLayout() {

        recyclerViewVerse = findViewById(R.id.poetry_verse);
        classicRecyclerView = findViewById(R.id.poetry_classics);

        recyclerViewVerse.setLayoutManager(new LinearLayoutManager(this));
        classicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVerse.addItemDecoration(new SpaceItemDecorationUtil(30));
        classicRecyclerView.addItemDecoration(new SpaceItemDecorationUtil(20));

        commentText = findViewById(R.id.poetry_show_comment);
        translationText = findViewById(R.id.poetry_show_translation);
        appreciationText = findViewById(R.id.poetry_show_appreciation);
        textText = findViewById(R.id.poetry_show_text);
        authorSummaryText = findViewById(R.id.poetry_author_summary);
        classicRecyclerView = findViewById(R.id.poetry_classics);
        titleText = findViewById(R.id.poetry_show_title);
        authorText = findViewById(R.id.poetry_show_author);

        find = findViewById(R.id.find);
        findBox = findViewById(R.id.find_box);
        back = findViewById(R.id.back);

        find.setOnClickListener(this);
        back.setOnClickListener(this);
        commentText.setOnClickListener(this);
        translationText.setOnClickListener(this);
        appreciationText.setOnClickListener(this);

        viewList.add(commentText);
        viewList.add(translationText);
        viewList.add(appreciationText);

        findBox.setIconifiedByDefault(false);
        findBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                findText = query;
                Jump.jump(findText, getContext());
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                findText = newText;
                return false;
            }
        });

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_show;
    }

    @Override
    protected void init() {
        poetry = (PoetryBean) DataSet.getObjectData("poetry");
        if (poetry != null){
            config();
            DataSet.removeObjectData("poetry");
        } else {
            Intent intent = getIntent();
            String url = intent.getStringExtra("url");
            if (url != null){
                mPresenter.getPoetry(url);
            }
        }

    }

    @Override
    public void showWorksSuccess(PoetryBean poetry) {
        this.poetry = poetry;
        config();
    }

    @Override
    public void showWorksSuccess2(final PoetryBean poetry2) {
        Log.e("TAG", "showWorksSuccess2: "+poetry2.toString());
        this.poetry = poetry2;
        final PoetryShowsVerseAdapter verseAdapter = new PoetryShowsVerseAdapter(this, poetry2.getVerses(), R.layout.activity_poetry_show_verse, this);
//        final PoetryShowsVerseAdapter classicAdapter = new PoetryShowsVerseAdapter(this, poetry.getClassics(), R.layout.activity_poetry_show_classic, this);
        recyclerViewVerse.post(new Runnable() {
            @Override
            public void run() {
                titleText.setText(poetry2.getName());
                authorText.setText("["+poetry2.getDynasty() + "] " + poetry2.getAuthorName());
                recyclerViewVerse.setAdapter(verseAdapter);
                changeText(0);
                authorSummaryText.setText(poetry2.getAuthor().getSummary());
//                classicRecyclerView.setAdapter(classicAdapter);
            }
        });
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

        titleText.setText(poetry.getName());
        authorText.setText("["+poetry.getDynasty() + "] " + poetry.getAuthorName());
        PoetryShowsVerseAdapter verseAdapter = new PoetryShowsVerseAdapter(this, poetry.getVerses(), R.layout.activity_poetry_show_verse, this);
        recyclerViewVerse.setAdapter(verseAdapter);

        changeText(0);
        authorSummaryText.setText(poetry.getAuthor().getSummary());

        if (poetry.getClassics() == null || poetry.getClassics().size() == 0){
            findViewById(R.id.poetry_classics_title).setVisibility(View.GONE);
        } else {
            classicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            PoetryShowsVerseAdapter classicAdapter = new PoetryShowsVerseAdapter(this, poetry.getClassics(), R.layout.activity_poetry_show_classic, this);
            classicRecyclerView.setAdapter(classicAdapter);
        }

    }

    private String initText(int position){
        String text = "";
        if (poetry == null){
            return "";
        }
        if (position == 0 && poetry.getComments() != null){
            for (CommentBean c:poetry.getComments()){
                text = text + c.getField()+": "+c.getText()+"\n";
            }
        } else if (position == 1 && poetry.getVerses() != null){
            for (VerseBean v : poetry.getVerses()){
                text = text + v.getTranslation() + "\n";
            }
        } else if (position == 2 && poetry.getAppreciations() != null){
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
                changeText(2);
                break;

            case R.id.find:
                Jump.jump(findText, getContext());
                break;

            case R.id.back:
                finish();
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
