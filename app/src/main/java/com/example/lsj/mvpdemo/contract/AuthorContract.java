package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.AuthorBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.view.IView;

import java.util.List;

public interface AuthorContract {

    interface View extends IView{
        void getAuthorsSuccess(List<AuthorBean> authors);
        void getPoetrySuccess(List<PoetryWorksBean> poetrys);
    }

    interface Presenter{
        void getAuthors();
        void getPoetrys(String id);
    }

}
