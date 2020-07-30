package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.view.IView;

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
