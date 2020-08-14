package com.example.lsj.mvp.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lsj.mvp.adapter.PoetryLibraryPagerApapter;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.presenter.IPresenter;
import com.example.lsj.mvp.utils.Jump;
import com.example.lsj.mvpdemo.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;


public class PoetryLibraryFragment extends BaseFragment implements View.OnClickListener {
    private String[] tabText = new String[]{"分类", "作品", "作者"};

    private List<Fragment> fragments = new ArrayList<>();

    private SegmentTabLayout mTab;
    private ViewPager mVp;
    private SearchView findBox;
    private TextView find;
    private ImageView poetrySearch;
    private ImageView back;
    private String findText;

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
        find = view.findViewById(R.id.find);
        findBox = (SearchView) view.findViewById(R.id.find_box);
        back = view.findViewById(R.id.back);
        poetrySearch = view.findViewById(R.id.poetry_search);

        findBox.setVisibility(View.GONE);
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

        poetrySearch.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {
        configViews();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.poetry_search:
                findBox.setVisibility(View.VISIBLE);
                find.setVisibility(View.VISIBLE);
                poetrySearch.setVisibility(View.GONE);
                mTab.setVisibility(View.GONE);
                ((EasyNavigationBar)getActivity().findViewById(R.id.navigationBar)).getNavigationLayout().setVisibility(View.GONE);
                break;
            case R.id.find:
                if (findText == null || findText.equals("")){
                    return;
                }
                findBox.setVisibility(View.GONE);
                find.setVisibility(View.GONE);
                poetrySearch.setVisibility(View.VISIBLE);
                mTab.setVisibility(View.VISIBLE);
                ((EasyNavigationBar)getActivity().findViewById(R.id.navigationBar)).getNavigationLayout().setVisibility(View.VISIBLE);
                Jump.jump(findText, getContext());
                break;
            case R.id.back:
                if (find.getVisibility() == View.VISIBLE){
                    findBox.setVisibility(View.GONE);
                    find.setVisibility(View.GONE);
                    poetrySearch.setVisibility(View.VISIBLE);
                    mTab.setVisibility(View.VISIBLE);
                    ((EasyNavigationBar)getActivity().findViewById(R.id.navigationBar)).getNavigationLayout().setVisibility(View.VISIBLE);
                }

        }
    }



}
