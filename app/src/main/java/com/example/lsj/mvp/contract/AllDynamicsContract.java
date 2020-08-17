package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.view.IView;

import java.util.List;

public interface AllDynamicsContract {

    interface View extends IView{
        void getDynamicSuccess(List<DynamicBean> dynamics);
    }

    interface Presenter{
        void getDynamicAll();
        void getDynamicByUser(String id);
    }

}
