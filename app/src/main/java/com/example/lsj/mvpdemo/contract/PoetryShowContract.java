package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.view.IView;

public interface PoetryShowContract {

    interface View extends IView {
        void showWorksSuccess(PoetryBean beans);

        void showWorksFail();
    }

    interface presenter{
        void getPoetryItem(String id);
    }

}
