package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.view.IView;

public interface UpdateDataContract {

    interface View extends IView{
        void updateDateSuccess();
        void updateDateFail();
    }

    interface Presenter{
        void updateData(String field, String context, String id);
    }

}
