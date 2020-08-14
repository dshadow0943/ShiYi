package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.AuthorAdapter;
import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.PoetryType;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.contract.AuthorContract;
import com.example.lsj.mvp.presenter.AuthorPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.view.activity.PoetryListActivity;
import com.example.lsj.mvpdemo.R;

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
    public void init() {
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
        //根据作者名批量爬取数据
        for (AuthorBean a: authors){
//            new ReptileTestSave().getPoetryItem(a.getName());
        }
    }

    @Override
    public void getPoetrysSuccess(List<PoetryWorksBean> poetrys) {
        PoetryType poetryType = new PoetryType(author.getName(), "img/ic_default.jpg", author.getSummary());
        poetryType.setPoetrys(poetrys);
        DataSet.putObject("poetryType", poetryType);
        startActivity(new Intent(getContext(), PoetryListActivity.class));
    }

    private void display(){
        authorRv.setLayoutManager(new LinearLayoutManager(getContext()));
        authorAdapter = new AuthorAdapter(getContext(), authors, R.layout.fragment_author, this);
        authorRv.setAdapter(authorAdapter);
    }

}
