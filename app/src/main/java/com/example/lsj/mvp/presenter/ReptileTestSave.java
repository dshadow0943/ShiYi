package com.example.lsj.mvp.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.lsj.mvp.api.PoetryApi;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.reptile.ReptileTest;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReptileTestSave {

    public void getPoetryItem(final String value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PoetryWorksBean> poetryItem = ReptileTest.getPoetryItem(value);
                if (poetryItem != null){
                    for (PoetryWorksBean w : poetryItem){
                        getPoetry(w.getUrl());
                    }
                }
                else {

                }
            }
        }).start();
    }

    public void getPoetry(final String url) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                PoetryBean poetry = null;
                try {
                    poetry = ReptileTest.getPoetry(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (poetry != null){

                    if (poetry.getComments().size() == 0 || poetry.getAppreciations().size() == 0){
                        return;
                    }
                    savePoetry(poetry);
                }
                else {

                }
            }
        }).start();
    }

    public void savePoetry(PoetryBean poetry) {
        new PoetryApi().savePoetry(poetry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
