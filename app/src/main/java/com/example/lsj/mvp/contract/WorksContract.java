package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.view.IView;

import java.util.List;

public interface WorksContract {

    interface View extends IView {
        void showWorksSuccess(List<PoetryWorksBean> poetrys);
        void getPoetrySuccess(PoetryBean poetry);
    }

    interface Presenter{
        void getWorks();
        void getPoetry(String id);
    }

}
