package com.example.lsj.mvpdemo.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.AuthorAdapter;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.AuthorBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.contract.AuthorContract;
import com.example.lsj.mvpdemo.presenter.AuthorPresenter;
import com.example.lsj.mvpdemo.utils.DataSet;
import com.example.lsj.mvpdemo.view.activity.FindPoetryItemActivity;

import java.util.List;

public class AuthorFragment extends BaseFragment<AuthorPresenter> implements AuthorContract.View, CommonRecyclerHolder.onClickCommonListener {

    RecyclerView authorRv;
    AuthorAdapter authorAdapter;
    List<AuthorBean> authors;
    AuthorBean author;

    public static Fragment newInstance(){
        return new AuthorFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_author_list;
    }

    @Override
    protected void bindinLayout() {
        authorRv = view.findViewById(R.id.authors);
    }

    @Override
    protected AuthorPresenter createPresenter() {
        return new AuthorPresenter();
    }

    @Override
    protected void init() {
        Log.e("Author", "init: ");
        mPresenter.getAuthors();
    }

    @Override
    public void onClickListener(View view, int position) {
        author = authors.get(position);
        mPresenter.getPoetrys(author.getId());
    }

    @Override
    public void onLongClickListener(View view, int position) {

    }

    @Override
    public void getAuthorsSuccess(List<AuthorBean> authors) {
        this.authors = authors;
        display();
    }

    @Override
    public void getPoetrySuccess(List<PoetryWorksBean> poetrys) {
        DataSet.putObject("poetrys", poetrys);
//        PoetryType poetryType = new PoetryType(author.getName(), "111", author.getSummary());
//        DataSet.putObject("poetryType", poetryType);
        startActivity(new Intent(getContext(), FindPoetryItemActivity.class));
    }

    private void display(){
        authorRv.setLayoutManager(new LinearLayoutManager(getContext()));
        authorAdapter = new AuthorAdapter(getContext(), authors, R.layout.fragment_author, this);
        authorRv.setAdapter(authorAdapter);
    }

}
