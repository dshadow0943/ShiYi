package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.PoetryWroksAdapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.contract.WorksContract;
import com.example.lsj.mvp.presenter.WorksPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.view.activity.PoetryShowActivity;
import com.example.lsj.mvpdemo.R;

import java.util.List;


public class WorksFragment extends BaseFragment<WorksPresenter> implements WorksContract.View, CommonRecyclerHolder.onClickCommonListener {

    private RecyclerView works;
    private PoetryWroksAdapter poetryWroksAdapter;
    private List<PoetryWorksBean> worksBeans;

    public static WorksFragment newInstance() {
        return new WorksFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_works_list;
    }

    @Override
    protected void bindinLayout() {
        works = view.findViewById(R.id.works);
    }

    @Override
    protected WorksPresenter createPresenter() {
        return new WorksPresenter();
    }

    @Override
    public void init() {
        mPresenter.getWorks();
    }

    @Override
    public void showWorksSuccess(List<PoetryWorksBean> poetrys) {
        worksBeans = poetrys;
        works.setLayoutManager(new LinearLayoutManager(getContext()));
        poetryWroksAdapter = new PoetryWroksAdapter(getContext(), poetrys, R.layout.fragment_poetry_work, this);
        works.setAdapter(poetryWroksAdapter);
    }

    @Override
    public void getPoetrySuccess(PoetryBean poetry) {
        DataSet.putObject("poetry", poetry);
        startActivity(new Intent(getContext(), PoetryShowActivity.class));
    }

    @Override
    public void onClickListener(View view, int position) {
        mPresenter.getPoetry(worksBeans.get(position).getId());
    }

    @Override
    public void onLongClickListener(View view, int position) {

    }
}
