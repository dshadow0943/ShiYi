package com.example.lsj.mvpdemo.view.fragment;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.contract.AppreciateContract;
import com.example.lsj.mvpdemo.presenter.AppreciatePresenter;

public class AppreciateFragment extends BaseFragment<AppreciatePresenter> implements AppreciateContract.View {


    public static AppreciateFragment newInstance() {
        return new AppreciateFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_appreciate;
    }

    @Override
    protected void bindinLayout() {

    }

    @Override
    protected AppreciatePresenter createPresenter() {
        return new AppreciatePresenter();
    }

    @Override
    protected void init() {

    }

}
