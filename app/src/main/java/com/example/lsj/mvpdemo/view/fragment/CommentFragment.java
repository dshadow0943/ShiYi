package com.example.lsj.mvpdemo.view.fragment;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.contract.CommentContract;
import com.example.lsj.mvpdemo.presenter.CommentPredenter;

import java.util.List;

public class CommentFragment extends BaseFragment<CommentPredenter> implements CommentContract.View {

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void bindinLayout() {

    }

    @Override
    protected CommentPredenter createPresenter() {
        return new CommentPredenter();
    }

    @Override
    protected void init() {
        mPresenter.getCommentItem();
    }

    @Override
    public void showCommentsSuccess(List<ClassificationItem> beans) {

    }

    @Override
    public void showCommentsFail(String errorMsg) {

    }
}
