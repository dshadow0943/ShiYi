package com.example.lsj.mvpdemo.view.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.PoetryListPagerAdapter;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.bean.PoetryListTabEntityBean;
import com.example.lsj.mvpdemo.contract.PoetryListContract;
import com.example.lsj.mvpdemo.presenter.PoetryListPresenter;
import com.example.lsj.mvpdemo.view.fragment.PoetryWorksFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

public class PoetryListActivity extends BaseActivity<PoetryListPresenter> implements PoetryListContract.View {

    private ViewPager mViewPager;
    private CommonTabLayout mTab;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected PoetryListPresenter createPresenter() {
        return new PoetryListPresenter();
    }

    @Override
    protected void bindinLayout() {
        mViewPager = findViewById(R.id.poetry_list_vp);
        mTab = findViewById(R.id.poetry_list_tab);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_list;
    }

    @Override
    protected void init() {
        fragmentList.add(PoetryWorksFragment.newInstance());
        fragmentList.add(PoetryWorksFragment.newInstance());
        fragmentList.add(PoetryWorksFragment.newInstance());

        mTabEntities.add(new PoetryListTabEntityBean("作品",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        mTabEntities.add(new PoetryListTabEntityBean("摘录",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        mTabEntities.add(new PoetryListTabEntityBean("社区",R.mipmap.ic_launcher,R.mipmap.ic_launcher));

        PoetryListPagerAdapter viewPagerAadpter = new PoetryListPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(viewPagerAadpter);
        mTab.setTabData(mTabEntities);

        mViewPager.setOffscreenPageLimit(3);

    }


}
