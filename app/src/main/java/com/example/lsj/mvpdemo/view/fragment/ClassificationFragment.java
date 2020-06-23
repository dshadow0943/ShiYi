package com.example.lsj.mvpdemo.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.ClassificationAdapter;
import com.example.lsj.mvpdemo.bean.ClassificationItem;

import java.util.ArrayList;
import java.util.List;

public class ClassificationFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "cft-count";

    private int mColumnCount = 1;

    private RecyclerView recyclerView;
    private ClassificationAdapter cftAdaptr;
    private List<ClassificationItem> cfts;

    public static ClassificationFragment newInstance() {
        ClassificationFragment fragment = new ClassificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classification_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.cft);
        Log.e("TAG", "onViewCreated: ");
        init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void init(){
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        cftAdaptr = new ClassificationAdapter(cfts);
        recyclerView.setAdapter(cftAdaptr);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
