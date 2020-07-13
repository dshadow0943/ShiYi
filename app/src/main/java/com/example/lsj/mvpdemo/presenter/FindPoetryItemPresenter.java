package com.example.lsj.mvpdemo.presenter;

import com.example.lsj.mvpdemo.base.BasePresenter;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.contract.FindPoetryItemContract;
import com.example.lsj.mvpdemo.reptile.ReptileTest;

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
