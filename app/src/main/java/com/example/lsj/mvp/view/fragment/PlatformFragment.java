package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lsj.mvp.adapter.PoetryLibraryPagerApapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.contract.PlatformContract;
import com.example.lsj.mvp.presenter.PlatformPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.view.activity.PublishDynamicActivity;
import com.example.lsj.mvp.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class PlatformFragment extends BaseFragment<PlatformPresenter> implements PlatformContract.View, View.OnClickListener {

    private String[] tabText = new String[]{"广场", "关注"};

    private List<Fragment> fragments = new ArrayList<>();

    private SegmentTabLayout mTab;
    private ViewPager mVp;
    private TextView tPublish;

    public static PlatformFragment newInstance() {
        return new PlatformFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_platform;
    }

    @Override
    protected void bindinLayout() {
        mTab = view.findViewById(R.id.plat_tab);
        mVp = view.findViewById(R.id.plat_vp);
        tPublish = view.findViewById(R.id.plat_publish);

        tPublish.setOnClickListener(this);
    }

    @Override
    protected PlatformPresenter createPresenter() {
        return new PlatformPresenter();
    }

    @Override
    public void init() {
        configViews();
    }

    private void configViews() {
        fragments.add(AllDynamicsFragment.newInstance(0));
        fragments.add(FollowedDynamicsFragment.newInstance());

        mTab.setTabData(tabText);
        PoetryLibraryPagerApapter pagerAdapter = new PoetryLibraryPagerApapter(getChildFragmentManager(), fragments, getActivity());
        mVp.setAdapter(pagerAdapter);

        //滑动、点击切换页面
        mTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //滑动、点击切换页面
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTab.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plat_publish:
                if (DataSet.getUser() == null){
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_LONG).show();
                    return;
                }
                startActivityForResult(new Intent(getContext(), PublishDynamicActivity.class), 2);
                break;
        }
    }
}
