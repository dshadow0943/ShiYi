package com.example.lsj.mvpdemo.model;

import android.util.Log;

import com.example.lsj.mvpdemo.api.PoetryApi;
import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.interfaces.Callback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassificationModel implements IModel {

    public void getClassificationItem(final Callback callback){
        Observable<List<ClassificationItem>> observable = PoetryApi.getInstance().getClassificationItem();
        observable.subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
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
                        Log.e("TAG", "onError: 网络请求出错");
                        callback.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
