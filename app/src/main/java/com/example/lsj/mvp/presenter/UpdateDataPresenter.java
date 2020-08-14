package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.contract.UpdateDataContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateDataPresenter extends BasePresenter<UpdateDataContract.View> implements UpdateDataContract.Presenter {

    @Override
    public void updateData(String field, String context, String id) {
        poetryApi.updateUser(field, context, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("true")){
                            mView.updateDateSuccess();
                        }else if (s.equals("false")){
                            mView.updateDateFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            mView.updateDateFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
