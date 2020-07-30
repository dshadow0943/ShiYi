package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.PoetryWroksAdapter;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.contract.FindPoetryItemContract;
import com.example.lsj.mvp.presenter.FindPoetryItemPresenter;
import com.example.lsj.mvp.utils.Jump;
import com.example.lsj.mvpdemo.R;

import java.util.List;

public class FindPoetryItemActivity extends BaseActivity<FindPoetryItemPresenter>
        implements FindPoetryItemContract.View, CommonRecyclerHolder.onClickCommonListener, View.OnClickListener {

    private SearchView findBox;
    private TextView find;
    private ImageView back;
    private RecyclerView recyclerView;
    List<PoetryWorksBean> poetryItem;
    private String findText;

    @Override
    protected FindPoetryItemPresenter createPresenter() {
        return new FindPoetryItemPresenter();
    }

    @Override
    protected void bindinLayout() {
        find = findViewById(R.id.find);
        findBox = findViewById(R.id.find_box);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.poetry_item);
        find.setOnClickListener(this);
        back.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        return R.layout.activity_find_poetry_item;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("find");
        if (value != null){
            mPresenter.getPoetryItem(value);
        }
    }

    @Override
    public void getPoetryItemSuccess(List<PoetryWorksBean> poetryItem) {
        this.poetryItem = poetryItem;
        display();
    }

    private void display(){
        final PoetryWroksAdapter poetryWroksAdapter = new PoetryWroksAdapter(this, poetryItem, R.layout.fragment_poetry_work, this);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(poetryWroksAdapter);
            }
        });
    }

    @Override
    public void getPoetryItemFail() {

    }

    @Override
    public void onClickListener(View view, int position) {
        Intent intent = new Intent(this, PoetryShowActivity.class);
        intent.putExtra("url", poetryItem.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void onLongClickListener(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.find:
                Jump.jump(findText, this);
                break;
        }
    }
}
