package com.example.lsj.mvpdemo.presenter;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.contract.ClassificationContract;
import com.example.lsj.mvpdemo.interfaces.Callback;
import com.example.lsj.mvpdemo.model.ClassificationModel;

import java.util.List;

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
}
