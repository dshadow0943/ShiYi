package com.example.lsj.mvpdemo.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.ClassicVerseAdapter;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.VerseBean;
import com.example.lsj.mvpdemo.contract.ClassicVerseContract;
import com.example.lsj.mvpdemo.presenter.ClassicVersePresenter;

import java.util.List;

public class ClassicVerseFragment extends BaseFragment<ClassicVersePresenter> implements ClassicVerseContract.View, CommonRecyclerHolder.onClickCommonListener {

    RecyclerView recyclerView;

    public static ClassicVerseFragment newInstance() {
        return new ClassicVerseFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_classic_verse;
    }

    @Override
    protected void bindinLayout() {
        recyclerView = view.findViewById(R.id.verse_show);
    }

    @Override
    protected ClassicVersePresenter createPresenter() {
        return new ClassicVersePresenter();
    }

    @Override
    protected void init() {
        mPresenter.getVerse();
    }

    @Override
    public void showVerseSuccess(List<VerseBean> verses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ClassicVerseAdapter(getContext(), verses, R.layout.fragment_classic_verse_show, this));
    }

    @Override
    public void showVerseFail() {

    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongClickListener(View view, int position) {

    }
}
