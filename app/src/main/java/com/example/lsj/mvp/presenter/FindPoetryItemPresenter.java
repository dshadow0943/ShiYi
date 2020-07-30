package com.example.lsj.mvp.presenter;

import com.example.lsj.mvp.contract.FindPoetryItemContract;
import com.example.lsj.mvp.reptile.ReptileTest;
import com.example.lsj.mvp.base.BasePresenter;
import com.example.lsj.mvp.bean.PoetryWorksBean;

import java.util.List;

public class FindPoetryItemPresenter extends BasePresenter<FindPoetryItemContract.View> implements FindPoetryItemContract.Presenter {

    @Override
    public void getPoetryItem(final String value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PoetryWorksBean> poetryItem = ReptileTest.getPoetryItem(value);
                if (poetryItem != null){
                    mView.getPoetryItemSuccess(poetryItem);
                }
                else {
                    mView.getPoetryItemFail();
                }
            }
        }).start();
    }
}
