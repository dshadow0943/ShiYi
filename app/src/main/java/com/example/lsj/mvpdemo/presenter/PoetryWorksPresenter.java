package com.example.lsj.mvpdemo.presenter;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.contract.PoetryWorksContract;
import com.example.lsj.mvpdemo.interfaces.Callback;
import com.example.lsj.mvpdemo.model.PoetryWorksModel;

import java.util.List;

public class PoetryWorksPresenter extends BasePresenter<PoetryWorksContract.View> implements PoetryWorksContract.presenter {

    PoetryWorksModel poetryWorksModel;

    public PoetryWorksPresenter(){
        poetryWorksModel = new PoetryWorksModel();
    }

    @Override
    public void getPoetryItem(String label) {
        poetryWorksModel.getPoetryItem(label, new Callback() {
            @Override
            public void onSuccess(Object data) {
                mView.showWorksSuccess((List<PoetryWorksBean>) data);
            }

            @Override
            public void onFail(Object data) {

            }
        });
    }
}
