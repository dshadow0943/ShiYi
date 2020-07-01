package com.example.lsj.mvpdemo.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.adapter.PoetryWroksAdapter;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.WorksBean;
import com.example.lsj.mvpdemo.contract.PoetryWorksContract;
import com.example.lsj.mvpdemo.presenter.PoetryWorksPresenter;

import java.util.List;

public class PoetryWorksFragment extends BaseFragment<PoetryWorksPresenter> implements PoetryWorksContract.View, CommonRecyclerHolder.onClickCommonListener {

    private RecyclerView works;
    private PoetryWroksAdapter poetryWroksAdapter;

    public static PoetryWorksFragment newInstance() {
        return new PoetryWorksFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_poetry_works_list;
    }

    @Override
    protected void bindinLayout() {
        works = view.findViewById(R.id.works);
    }

    @Override
    protected PoetryWorksPresenter createPresenter() {
        return new PoetryWorksPresenter();
    }

    @Override
    protected void init() {
        mPresenter.getPoetryItem("爱");
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getContext(), "短按：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickListener(View view, int position) {
        Toast.makeText(getContext(), "长按：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWorksSuccess(List<WorksBean> beans) {
        Log.e("TAG", "showWorksSuccess: " + beans.toString());
        works.setLayoutManager(new LinearLayoutManager(getContext()));
        poetryWroksAdapter = new PoetryWroksAdapter(getContext(), beans, R.layout.fragment_poetry_work, this);
        works.setAdapter(poetryWroksAdapter);
    }

    @Override
    public void showWorksFail() {

    }
}
