package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.view.IView;

import java.util.List;

public interface ClassificationContract {

    interface View extends IView {

        void showClassificationSuccess(List<ClassificationItem> beans);

        void getPoetrysSuccess(List<PoetryWorksBean> poetrys);

        void showClassificationFail(String errorMsg);

    }

    interface Presenter {
        void getClassificationItem();

        void getPoetrys(String label);

    }
}
