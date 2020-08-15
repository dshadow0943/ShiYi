package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.view.IView;

public interface PublishDynamicContract {

    interface View extends IView{
        void publishSuccess();
    }

    interface Presenter{
        void submitDynamic(DynamicBean dynamic);
    }

}
