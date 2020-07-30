package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.bean.VerseBean;
import com.example.lsj.mvp.view.IView;

import java.util.List;

public interface ClassicVerseContract {

    interface View extends IView {
        void showVerseSuccess(List<VerseBean> verses);

        void showVerseFail();
    }

    interface Predenter{
        void getVerse();
    }

}
