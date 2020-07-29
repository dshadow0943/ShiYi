package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.view.IView;

import java.util.List;

public interface WorksContract {

    interface View extends IView{
        void showWorksSuccess(List<PoetryWorksBean> poetrys);
        void getPoetrySuccess(PoetryBean poetry);
    }

    interface Presenter{
        void getWorks();
        void getPoetry(String id);
    }

}
