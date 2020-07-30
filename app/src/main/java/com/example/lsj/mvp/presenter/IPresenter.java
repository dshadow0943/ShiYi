package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.view.IView;

public interface IPresenter<T extends IView> {

    /**
     * 依附生命view
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 分离View
     */
    void detachView();

    /**
     * 判断View是否已经销毁
     *
     * @return
     */
    boolean isViewAttached();

}

