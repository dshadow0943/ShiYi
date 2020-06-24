package com.example.lsj.mvpdemo.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.ClassificationItemAdapter;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.bean.ClassificationBean;
import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.contract.ClassificationContract;
import com.example.lsj.mvpdemo.presenter.ClassificationPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClassificationFragment extends BaseFragment<ClassificationPresenter> implements ClassificationContract.View {

    private RecyclerView recyclerView;
    private ClassificationItemAdapter cftAdaptr;
    private List<ClassificationItem> cfts;

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
        cfts = new ArrayList<>();
        cfts.add(new ClassificationItem("选集"));
        cfts.add(new ClassificationItem("主题"));
        cfts.add(new ClassificationItem("写景"));
        cfts.add(new ClassificationItem("节日"));
        cfts.add(new ClassificationItem("节气"));
        cfts.add(new ClassificationItem("词牌"));
        cfts.add(new ClassificationItem("时令"));
        cfts.add(new ClassificationItem("课本"));
        cfts.add(new ClassificationItem("地理"));
        cfts.add(new ClassificationItem("用典"));

        for (ClassificationItem l : cfts){
            for (int i = 0; i < 20; i++) {
                l.getCfts().add(new ClassificationBean("R.drawable/ic_lock_idle_lock", "词名"));
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        cftAdaptr = new ClassificationItemAdapter(cfts);
        recyclerView.setAdapter(cftAdaptr);
    }

}
