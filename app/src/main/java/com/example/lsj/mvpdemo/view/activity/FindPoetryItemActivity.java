package com.example.lsj.mvpdemo.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.adapter.PoetryWroksAdapter;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.contract.FindPoetryItemContract;
import com.example.lsj.mvpdemo.presenter.FindPoetryItemPresenter;
import com.example.lsj.mvpdemo.utils.DataSet;

import java.util.List;

public class FindPoetryItemActivity extends BaseActivity<FindPoetryItemPresenter>
        implements FindPoetryItemContract.View, CommonRecyclerHolder.onClickCommonListener, View.OnClickListener {

    private EditText findBox;
    private TextView find;
    private ImageView back;
    private RecyclerView recyclerView;
    List<PoetryWorksBean> poetryItem;

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
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_find_poetry_item;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("find");
        poetryItem = (List<PoetryWorksBean>) DataSet.getObjectData("poetrys");
        if (value != null){
            mPresenter.getPoetryItem(value);
        }else if (poetryItem != null){
            display();
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
                if (findBox.getText().toString().equals("")){
                    return;
                }
                String value = findBox.getText().toString();
                findBox.setText("");
                mPresenter.getPoetryItem(value);
                break;
        }
    }
}
