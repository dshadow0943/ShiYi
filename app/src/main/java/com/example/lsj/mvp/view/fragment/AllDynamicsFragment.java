package com.example.lsj.mvp.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.DynamicAdapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.contract.AllDynamicsContract;
import com.example.lsj.mvp.presenter.AllDynamicsPresenter;
import com.example.lsj.mvpdemo.R;

import java.util.List;

public class AllDynamicsFragment extends BaseFragment<AllDynamicsPresenter> implements AllDynamicsContract.View, CommonRecyclerHolder.onClickCommonListener {

    private RecyclerView rDynamic;
    List<DynamicBean> dynamics;

    public static AllDynamicsFragment newInstance() {
        return new AllDynamicsFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_all_dynamics;
    }

    @Override
    protected void bindinLayout() {
        rDynamic = view.findViewById(R.id.dynamic_all);
    }

    @Override
    protected AllDynamicsPresenter createPresenter() {
        return new AllDynamicsPresenter();
    }

    @Override
    public void init() {
        mPresenter.getDynamicAll();
    }

    @Override
    public void getDynamicSuccess(List<DynamicBean> dynamics) {
        this.dynamics = dynamics;
        display();
    }

    private void display(){
        rDynamic.setLayoutManager(new LinearLayoutManager(getContext()));
        DynamicAdapter adapter = new DynamicAdapter(getContext(), dynamics, R.layout.recylier_dynamic, this);
        rDynamic.setAdapter(adapter);
    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongClickListener(View view, int position) {

    }
}
