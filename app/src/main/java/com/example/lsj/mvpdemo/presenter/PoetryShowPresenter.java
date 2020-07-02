package com.example.lsj.mvpdemo.presenter;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.contract.PoetryShowContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PoetryShowPresenter extends BasePresenter<PoetryShowContract.View> implements PoetryShowContract.presenter {


    @Override
    public void getPoetryItem(String id) {
        poetryApi.getPoetryById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PoetryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PoetryBean poetryBean) {
                        mView.showWorksSuccess(poetryBean);
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
