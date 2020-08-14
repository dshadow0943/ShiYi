package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.ClassicVerseAdapter;
import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.VerseBean;
import com.example.lsj.mvp.contract.ClassicVerseContract;
import com.example.lsj.mvp.presenter.ClassicVersePresenter;
import com.example.lsj.mvp.utils.Jump;
import com.example.lsj.mvp.view.activity.FindPoetryItemActivity;
import com.example.lsj.mvpdemo.R;

import java.util.List;

public class ClassicVerseFragment extends BaseFragment<ClassicVersePresenter> implements ClassicVerseContract.View, CommonRecyclerHolder.onClickCommonListener, View.OnClickListener {

    RecyclerView recyclerView;
    private SearchView findBox;
    private TextView find;
    private TextView back;
    private String findText;

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
        find = view.findViewById(R.id.find);
        findBox = view.findViewById(R.id.find_box);
        find.setOnClickListener(this);

        findBox.setIconifiedByDefault(false);
        findBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                findText = query;
                Jump.jump(findText, getContext());
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                findText = newText;
                return false;
            }
        });
    }

    @Override
    protected ClassicVersePresenter createPresenter() {
        return new ClassicVersePresenter();
    }

    @Override
    public void init() {
        mPresenter.getVerse();
    }

    @Override
    public void showVerseSuccess(List<VerseBean> verses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:

                break;
            case R.id.find:
                Jump.jump(findText, getContext());
                break;
        }
    }

    private void jump(){
        if (findText == null || findText.equals("")){
            return;
        }
        Intent intent = new Intent(getContext(), FindPoetryItemActivity.class);
        intent.putExtra("find", findText);
        startActivity(intent);
    }

}
