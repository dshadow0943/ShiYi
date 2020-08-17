package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.AccountSettingContract;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccountSettingPresenter extends BasePresenter<AccountSettingContract.View> implements AccountSettingContract.Presenter {

    @Override
    public void getUserData(String id) {
        poetryApi.getUserDateById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        mView.getUserDataSuccess(userBean);
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
    public void uploadAvatar(String path) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

        // MultipartBody.Part 和后端约定好Key，这里的partName是用file
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "avatar";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        poetryApi.uploadAvatar(description, body)
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
