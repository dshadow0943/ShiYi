package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.view.IView;

public interface PoetryShowContract {

    interface View extends IView {
        void showWorksSuccess(PoetryBean beans);
        void showWorksSuccess2(PoetryBean poetry);
        void showWorksFail();
    }

    interface presenter{
        void getPoetryItem(String id);

        void getPoetry(String url);

        void savePoetry(PoetryBean poetry);
    }

}
