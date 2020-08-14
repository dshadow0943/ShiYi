package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.view.IView;

public interface RegisterContract {

    interface View extends IView{
        void registerSuccess();
        void registerFail();
    }

    interface Presenter{
        void registerUser(UserBean user);
    }

}
