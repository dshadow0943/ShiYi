package com.example.lsj.mvpdemo.view.fragment;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.PoetryLibraryApapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PoetryLibraryFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "poetry-count";
    private int mColumnCount = 1;

    private String[] tabText = new String[]{"分类", "作品", "作者"};
    //未选中icon
    private int[] normalIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    //选中时icon
    private int[] selectIcon = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    private List<Fragment> fragments = new ArrayList<>();

    private SlidingTabLayout mTab;
    private ViewPager mVp;


    public static PoetryLibraryFragment newInstance() {
        PoetryLibraryFragment fragment = new PoetryLibraryFragment();
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
        return inflater.inflate(R.layout.fragment_poetry_library, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = Objects.requireNonNull(getActivity()).getActionBar();
        Log.e("TAG", "actionBar: "+actionBar);
        if (actionBar != null){
            actionBar.hide();
        }
        mTab = view.findViewById(R.id.poetry_tab);
        mVp = view.findViewById(R.id.poetry_vp);
        configViews();

    }

    public void configViews() {
        fragments.add(ClassificationFragment.newInstance());
        fragments.add(WorksFragment.newInstance());
        fragments.add(AuthorFragment.newInstance());

        PoetryLibraryApapter pagerAdapter = new PoetryLibraryApapter(getChildFragmentManager(), fragments, getActivity());
        mVp.setAdapter(pagerAdapter);
        mTab.setViewPager(mVp, tabText);//tab和ViewPager进行关联
    }

}
