package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.view.IView;

public interface AccountSettingContract {

    interface View extends IView{
        void getUserDataSuccess(UserBean user);
    }

    interface Presenter{
        void getUserData(String id);
    }

}
