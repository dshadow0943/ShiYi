package com.example.lsj.mvp.base;

import com.example.lsj.mvp.presenter.IPresenter;
import com.example.lsj.mvp.view.IView;
import com.example.lsj.mvp.api.PoetryApi;

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
