package com.example.lsj.mvpdemo.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.PoetryLibraryPagerApapter;
import com.example.lsj.mvpdemo.base.BaseFragment;
import com.example.lsj.mvpdemo.presenter.IPresenter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;


public class PoetryLibraryFragment extends BaseFragment {
    private String[] tabText = new String[]{"分类", "作品", "作者"};
    //未选中icon
    private int[] normalIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    //选中时icon
    private int[] selectIcon = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    private List<Fragment> fragments = new ArrayList<>();

    private SegmentTabLayout mTab;
    private ViewPager mVp;

    public static PoetryLibraryFragment newInstance() {
        return new PoetryLibraryFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_poetry_library;
    }

    @Override
    protected void bindinLayout() {
        mTab = view.findViewById(R.id.poetry_tab);
        mVp = view.findViewById(R.id.poetry_vp);
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        configViews();

//        startActivity(new Intent(getContext(), PoetryListActivity.class));
    }

    public void configViews() {
        fragments.add(ClassificationFragment.newInstance());
        fragments.add(WorksFragment.newInstance());
        fragments.add(AuthorFragment.newInstance());

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

}
