package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.view.IView;

import java.util.List;

public interface AuthorContract {

    interface View extends IView {
        void getAuthorsSuccess(List<AuthorBean> authors);
        void getPoetrysSuccess(List<PoetryWorksBean> poetrys);
    }

    interface Presenter{
        void getAuthors();
        void getPoetrys(String id);
    }

}
