package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.contract.PublishDynamicContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PublishDynamicPresenter extends BasePresenter<PublishDynamicContract.View> implements PublishDynamicContract.Presenter {
    @Override
    public void submitDynamic(DynamicBean dynamic) {
        poetryApi.submitDynamic(dynamic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("true")){
                            mView.publishSuccess();
                        }
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
