package com.example.lsj.mvpdemo.contract;

import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.view.IView;

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
