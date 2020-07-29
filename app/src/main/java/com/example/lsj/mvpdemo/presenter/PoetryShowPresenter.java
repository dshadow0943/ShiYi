package com.example.lsj.mvpdemo.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.contract.PoetryShowContract;
import com.example.lsj.mvpdemo.reptile.ReptileTest;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PoetryShowPresenter extends BasePresenter<PoetryShowContract.View> implements PoetryShowContract.presenter {

    private static final String TAG = "PoetryShowPresenter";

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

    @Override
    public void getPoetry(final String url) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                PoetryBean poetry = null;
                try {
                    poetry = ReptileTest.getPoetry2(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (poetry != null){
                    mView.showWorksSuccess2(poetry);
//                    if (poetry.getComments().size() == 0 || poetry.getAppreciations().size() == 0){
//                        return;
//                    }
                    Log.e(TAG, "getPoetry: " + poetry.toString());
                    savePoetry(poetry);
                }
                else {
                    mView.showWorksFail();
                }
            }
        }).start();
    }

    @Override
    public void savePoetry(PoetryBean poetry) {
        poetryApi.savePoetry(poetry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showWorksFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
