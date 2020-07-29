package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.view.IView;

import java.util.List;

public interface FindPoetryItemContract {

    interface View extends IView{
        void getPoetryItemSuccess(List<PoetryWorksBean> poetryItem);


        void getPoetryItemFail();
    }

    interface Presenter{
        void getPoetryItem(String value);
    }

}
