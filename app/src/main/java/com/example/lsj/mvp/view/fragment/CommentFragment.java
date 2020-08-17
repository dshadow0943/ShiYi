package com.example.lsj.mvp.view.fragment;

import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.R;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.contract.CommentContract;
import com.example.lsj.mvp.presenter.CommentPredenter;

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
    public void init() {
        mPresenter.getCommentItem();
    }

    @Override
    public void showCommentsSuccess(List<ClassificationItem> beans) {

    }

    @Override
    public void showCommentsFail(String errorMsg) {

    }
}
