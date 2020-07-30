package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.contract.ClassificationContract;
import com.example.lsj.mvp.interfaces.Callback;
import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.model.ClassificationModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassificationPresenter extends BasePresenter<ClassificationContract.View> implements ClassificationContract.Presenter {

    private ClassificationModel classificationModel;

    public ClassificationPresenter(){
        classificationModel = new ClassificationModel();
    }

    @Override
    public void getClassificationItem() {
        classificationModel.getClassificationItem(new Callback() {
            @Override
            public void onSuccess(Object data) {
                mView.showClassificationSuccess((List<ClassificationItem>) data);
            }

            @Override
            public void onFail(Object data) {

            }
        });
    }

    @Override
    public void getPoetrys(String label) {
        poetryApi.getWorksBeanItem(label)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PoetryWorksBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PoetryWorksBean> beans) {
                        mView.getPoetrysSuccess(beans);
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
