package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.view.IView;

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
