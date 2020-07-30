package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.interfaces.Callback;
import com.example.lsj.mvp.model.ClassicVerseModle;
import com.example.lsj.mvp.bean.VerseBean;
import com.example.lsj.mvp.contract.ClassicVerseContract;

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
