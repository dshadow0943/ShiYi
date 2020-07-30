package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.view.IView;

public interface PoetryWorksContract {

    interface View extends IView {
        void getPoetrySuccess(PoetryBean poetry);

        void showWorksFail();
    }

    interface presenter{
        void getPoetry(String id);
    }

}
