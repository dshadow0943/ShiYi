package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.view.IView;

public interface LoginContract {

    interface View extends IView{
        void loginSuccess(UserBean user);
        void loginFail(UserBean user);
    }

    interface Presenter{
        void loginUser(String phone, String pwd);
    }
}
