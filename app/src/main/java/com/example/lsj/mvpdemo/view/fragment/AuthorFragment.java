package com.example.lsj.mvpdemo.view.fragment;

import androidx.fragment.app.Fragment;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.contract.AuthorContract;
import com.example.lsj.mvpdemo.presenter.AuthorPresenter;

public class AuthorFragment extends BaseFragment<AuthorPresenter> implements AuthorContract.View {

    public static Fragment newInstance(){
        return new AuthorFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_author_list;
    }

    @Override
    protected void bindinLayout() {

    }

    @Override
    protected AuthorPresenter createPresenter() {
        return new AuthorPresenter();
    }

    @Override
    protected void init() {

    }
}
