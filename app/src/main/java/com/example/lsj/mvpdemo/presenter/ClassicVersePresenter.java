package com.example.lsj.mvpdemo.presenter;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.contract.ClassicVerseContract;
import com.example.lsj.mvpdemo.interfaces.Callback;
import com.example.lsj.mvpdemo.model.ClassicVerseModle;

import java.util.List;

public class ClassicVersePresenter extends BasePresenter<ClassicVerseContract.View> implements ClassicVerseContract.Predenter {

    ClassicVerseModle classicVerseModle;

    public ClassicVersePresenter(){
        classicVerseModle = new ClassicVerseModle();
    }

    @Override
    public void getVerse() {
        classicVerseModle.getVerse(new Callback() {
            @Override
            public void onSuccess(Object data) {
                mView.showVerseSuccess((List<VerseBean>) data);
            }

            @Override
            public void onFail(Object data) {
                mView.showVerseFail();
            }
        });
    }
}
