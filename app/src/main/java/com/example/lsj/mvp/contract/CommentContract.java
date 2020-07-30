package com.example.lsj.mvp.contract;

import com.example.lsj.mvp.view.IView;
import com.example.lsj.mvp.bean.ClassificationItem;

import java.util.List;

public interface CommentContract {

    interface View extends IView {

        void showCommentsSuccess(List<ClassificationItem> beans);

        void showCommentsFail(String errorMsg);

    }

    interface Presenter {

        void getCommentItem();

    }

}
