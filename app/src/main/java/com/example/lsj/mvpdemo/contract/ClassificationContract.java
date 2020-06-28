package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.view.IView;

import java.util.List;

public interface ClassificationContract {

    interface View extends IView {

        void showClassificationSuccess(List<ClassificationItem> beans);

        void showClassificationFail(String errorMsg);

    }

    interface Presenter {

        void getClassificationItem();

    }
}
