package com.example.lsj.mvpdemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lsj.mvpdemo.presenter.IPresenter;
import com.example.lsj.mvpdemo.view.IView;

public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView{

    protected T mPresenter;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getViewId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        bindinLayout();
        initPresenter();
        init();
    }

    //获取ViewId
    protected abstract int getViewId();

    //绑定布局控件
    protected abstract void bindinLayout();

    //创建一个Presenter
    protected abstract T createPresenter();

//    public abstract Fragment newInstance();

    protected abstract void init();

    private void initPresenter() {
        mPresenter = createPresenter();
        //绑定生命周期
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
