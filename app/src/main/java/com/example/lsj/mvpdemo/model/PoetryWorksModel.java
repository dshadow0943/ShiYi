package com.example.lsj.mvpdemo.model;

import com.example.lsj.mvpdemo.base.BaseModel;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.interfaces.Callback;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PoetryWorksModel extends BaseModel {

    public void getPoetryItem(String label, final Callback callback){
        poetryApi.getWorksBeanItem(label)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PoetryWorksBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PoetryWorksBean> beans) {
                        callback.onSuccess(beans);
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
