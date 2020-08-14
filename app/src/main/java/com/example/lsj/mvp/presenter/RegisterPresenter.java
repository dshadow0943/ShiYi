package com.example.lsj.mvp.presenter;

import android.util.Log;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.RegisterContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void registerUser(UserBean user) {
        poetryApi.registerUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("true")){
                            mView.registerSuccess();
                        } else if(s.equals("false")){
                            mView.registerFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.toString());
                        mView.registerFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
