package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.PoetryWroksAdapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryType;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.contract.PoetryWorksContract;
import com.example.lsj.mvp.presenter.PoetryWorksPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.utils.LogUtil;
import com.example.lsj.mvp.view.activity.PoetryShowActivity;
import com.example.lsj.mvpdemo.R;
import com.google.gson.Gson;

import java.util.List;

public class PoetryWorksFragment extends BaseFragment<PoetryWorksPresenter> implements PoetryWorksContract.View, CommonRecyclerHolder.onClickCommonListener {

    private RecyclerView works;
    private PoetryWroksAdapter poetryWroksAdapter;
    private List<PoetryWorksBean> poetrys;

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
    public void init() {
        PoetryType poetryType = (PoetryType) DataSet.getObjectData("poetryType");
        if (poetryType == null){
            return;
        }
        poetrys = poetryType.getPoetrys();
        display();
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (view.getId()){
            case -1:
                mPresenter.getPoetry(poetrys.get(position).getId());
                break;
        }
    }

    @Override
    public void onLongClickListener(View view, int position) {
        Toast.makeText(getContext(), "长按：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPoetrySuccess(PoetryBean poetry) {
        DataSet.putObject("poetry", poetry);
        LogUtil.e("TAG", new Gson().toJson(poetry));
//        Log.e("TAG", "getPoetrySuccess: " + new Gson().toJson(poetry));
        startActivity(new Intent(getContext(), PoetryShowActivity.class));
    }

    @Override
    public void showWorksFail() {

    }

    public void display(){
        works.setLayoutManager(new LinearLayoutManager(getContext()));
        poetryWroksAdapter = new PoetryWroksAdapter(getContext(), poetrys, R.layout.fragment_poetry_work, this);
        works.setAdapter(poetryWroksAdapter);
    }

}
