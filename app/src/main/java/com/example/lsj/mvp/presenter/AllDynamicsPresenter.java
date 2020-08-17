package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.contract.AllDynamicsContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllDynamicsPresenter extends BasePresenter<AllDynamicsContract.View> implements AllDynamicsContract.Presenter {

    @Override
    public void getDynamicAll() {
        poetryApi.getDynamicAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DynamicBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DynamicBean> dynamicBeans) {
                        if (dynamicBeans != null){
                            mView.getDynamicSuccess(dynamicBeans);
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

    @Override
    public void getDynamicByUser(String id) {
        poetryApi.getDynamicByUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DynamicBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DynamicBean> dynamicBeans) {
                        if (dynamicBeans != null){
                            mView.getDynamicSuccess(dynamicBeans);
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
