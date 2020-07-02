package com.example.lsj.mvpdemo.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.ClassificationItemAdapter;
import com.example.lsj.mvpdemo.adapter.CommonRecyclerHolder;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.contract.ClassificationContract;
import com.example.lsj.mvpdemo.presenter.ClassificationPresenter;
import com.example.lsj.mvpdemo.view.activity.PoetryListActivity;

import java.util.List;

public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements CommonRecyclerHolder.onClickCommonListener, ClassificationContract.View {

    private RecyclerView recyclerView;
    private List<ClassificationItem> cfts;
    ClassificationItemAdapter classificationItemAdapter;

    public static Fragment newInstance() {
        return new ClassificationFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_classification_list;
    }

    @Override
    protected void bindinLayout() {
        recyclerView = view.findViewById(R.id.cft);
    }

    @Override
    protected ClassificationPresenter createPresenter() {
        return new ClassificationPresenter();
    }

    @Override
    protected void init(){
        mPresenter.getClassificationItem();
        Log.e("TAG", "init: ----");
    }

    @Override
    public void setOnclik(Class aClass) {
        super.setOnclik(aClass);
        startActivity(new Intent(getContext(), PoetryListActivity.class));
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (view.getId()){
            case -1 :

                break;
            case R.id.cft_more :
                Toast.makeText(getContext(), "点击更多：" + position, Toast.LENGTH_SHORT).show();
                break;
            default:    break;
        }

    }

    @Override
    public void onLongClickListener(View view, int position) {
        Toast.makeText(getContext(), "长按：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showClassificationSuccess(List<ClassificationItem> beans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        classificationItemAdapter = new ClassificationItemAdapter(getContext(), beans, R.layout.fragment_classification, this);
        recyclerView.setAdapter(classificationItemAdapter);
    }

    @Override
    public void showClassificationFail(String errorMsg) {
        Log.e("TAG", "showClassificationFail: "+ errorMsg);
    }
}
