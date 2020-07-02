package com.example.lsj.mvpdemo.model;

import com.example.lsj.mvpdemo.base.BaseModel;
import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.interfaces.Callback;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassicVerseModle extends BaseModel {

    public void getVerse(final Callback callback){
        poetryApi.getClassicVerseItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VerseBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<VerseBean> verseBeans) {
                        callback.onSuccess(verseBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
