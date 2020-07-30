package com.example.lsj.mvp.base;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lsj.mvp.presenter.IPresenter;
import com.example.lsj.mvp.view.IView;

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements IView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        statusBarSetting();
        bindinLayout();
        initPresenter();
        init();
    }

    //创建一个Presenter
    protected abstract T createPresenter();

    //绑定布局控件
    protected abstract void bindinLayout();

    //获取ViewId
    protected abstract int getViewId();

    protected abstract void init();

    public Context getContext(){
        return this;
    }

   //状态栏导航栏设置
    protected void statusBarSetting(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    protected void initPresenter() {
        mPresenter = createPresenter();
        //绑定生命周期
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

}