package com.example.lsj.mvp.presenter;

import android.util.Log;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.contract.AuthorContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorPresenter extends BasePresenter<AuthorContract.View> implements AuthorContract.Presenter {
    @Override
    public void getAuthors() {
        poetryApi.getAuthorItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AuthorBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<AuthorBean> authorBeans) {
                        mView.getAuthorsSuccess(authorBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Author", "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getPoetrys(String id) {
        poetryApi.getPoetryByAuthorId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PoetryWorksBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PoetryWorksBean> poetryWorksBeans) {
                        mView.getPoetrysSuccess(poetryWorksBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
