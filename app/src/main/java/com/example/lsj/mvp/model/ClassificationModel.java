package com.example.lsj.mvp.model;

import com.example.lsj.mvp.base.BaseModel;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.interfaces.Callback;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassificationModel extends BaseModel {

    public void getClassificationItem(final Callback callback){
        poetryApi.getClassificationItem()
                .subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
                .observeOn(AndroidSchedulers.mainThread()) // UI线程处理返回接口
                .subscribe(new Observer<List<ClassificationItem>>() { // 订阅
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ClassificationItem> classificationItems) {
                        callback.onSuccess(classificationItems);
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
