package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.view.IView;

import java.util.List;

public interface PoetryWorksContract {

    interface View extends IView{
        void showWorksSuccess(List<PoetryWorksBean> beans);

        void showWorksFail();
    }

    interface presenter{
        void getPoetryItem(String label);
    }

}
