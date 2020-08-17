package com.example.lsj.mvp.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvp.adapter.CommonRecyclerHolder;
import com.example.lsj.mvp.adapter.DynamicAdapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.AllDynamicsContract;
import com.example.lsj.mvp.presenter.AllDynamicsPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.R;

import java.util.List;

public class AllDynamicsFragment extends BaseFragment<AllDynamicsPresenter> implements AllDynamicsContract.View, CommonRecyclerHolder.onClickCommonListener {

    private RecyclerView rDynamic;
    private List<DynamicBean> dynamics;

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;

    public static AllDynamicsFragment newInstance(int param1) {
        AllDynamicsFragment fragment = new AllDynamicsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_all_dynamics;
    }

    @Override
    protected void bindinLayout() {
        rDynamic = view.findViewById(R.id.dynamic_all);
    }

    @Override
    protected AllDynamicsPresenter createPresenter() {
        return new AllDynamicsPresenter();
    }

    @Override
    public void init() {
        switch (mParam1){
            case 0:
                mPresenter.getDynamicAll();
                break;
            case 1:
                UserBean user = DataSet.getUser();
                if (user == null){
                    ((TextView)view.findViewById(R.id.dynamic_show_null)).setText("你当前处于未登录状态");
                    return;
                }
                mPresenter.getDynamicByUser(user.getId());
        }


    }

    @Override
    public void getDynamicSuccess(List<DynamicBean> dynamics) {
        if (dynamics == null || dynamics.size() == 0){
            ((TextView)view.findViewById(R.id.dynamic_show_null)).setText("你当前并未发布动态");
            return;
        }
        this.dynamics = dynamics;
        display();
    }

    private void display(){
        rDynamic.setLayoutManager(new LinearLayoutManager(getContext()));
        DynamicAdapter adapter = new DynamicAdapter(getContext(), dynamics, R.layout.recylier_dynamic, this);
        rDynamic.setAdapter(adapter);
    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongClickListener(View view, int position) {

    }
}
