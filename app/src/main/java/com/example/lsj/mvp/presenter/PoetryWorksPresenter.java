package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.contract.PoetryWorksContract;
import com.example.lsj.mvp.model.PoetryWorksModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PoetryWorksPresenter extends BasePresenter<PoetryWorksContract.View> implements PoetryWorksContract.presenter {

    PoetryWorksModel poetryWorksModel;

    public PoetryWorksPresenter(){
        poetryWorksModel = new PoetryWorksModel();
    }

    @Override
    public void getPoetry(String id) {
        poetryApi.getPoetryById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PoetryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PoetryBean poetry) {
                        mView.getPoetrySuccess(poetry);
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
