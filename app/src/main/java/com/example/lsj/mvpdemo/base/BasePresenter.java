package com.example.lsj.mvpdemo.base;

import com.example.lsj.mvpdemo.api.PoetryApi;
import com.example.lsj.mvpdemo.presenter.IPresenter;
import com.example.lsj.mvpdemo.view.IView;

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    public T mView;
    protected final static PoetryApi poetryApi = PoetryApi.getInstance();

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
