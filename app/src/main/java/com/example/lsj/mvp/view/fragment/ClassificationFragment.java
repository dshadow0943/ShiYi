package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.contract.ClassificationContract;
import com.example.lsj.mvp.presenter.ClassificationPresenter;
import com.example.lsj.mvp.view.activity.PoetryListActivity;
import com.example.lsj.mvp.R;
import com.example.lsj.mvp.adapter.ClassificationItemAdapter;
import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.bean.ClassificationBean;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.bean.PoetryType;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.utils.DataSet;

import java.util.List;

public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements CommonRecyclerHolder.onClickCommonListener, ClassificationContract.View {

    private RecyclerView recyclerView;
    ClassificationItemAdapter classificationItemAdapter;
    PoetryType poetryType;

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
    public void init(){
        mPresenter.getClassificationItem();
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
            case R.id.arrow_right:
                Toast.makeText(getContext(), "点击更多：" + position, Toast.LENGTH_SHORT).show();
                break;
            default:    break;
        }

    }

    @Override
    public void onLongClickListener(View view, int position) {
    }

    @Override
    public void showClassificationSuccess(List<ClassificationItem> beans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        classificationItemAdapter = new ClassificationItemAdapter(this, beans, R.layout.fragment_classification, this);
        recyclerView.setAdapter(classificationItemAdapter);
    }

    @Override
    public void getPoetrysSuccess(List<PoetryWorksBean> poetrys) {
        poetryType.setPoetrys(poetrys);
        DataSet.putObject("poetryType", poetryType);
        startActivity(new Intent(getContext(), PoetryListActivity.class));
    }

    @Override
    public void showClassificationFail(String errorMsg) {
    }

    public void sendData(ClassificationBean data){
        mPresenter.getPoetrys(data.getName());
        poetryType = new PoetryType(data.getName(), data.getImgPath(), " ");
    }
}
