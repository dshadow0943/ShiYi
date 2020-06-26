package com.example.lsj.mvpdemo.view.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.adapter.PoetryListPagerAdapter;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.bean.ClassificationBean;
import com.example.lsj.mvpdemo.bean.PoetryListTabEntityBean;
import com.example.lsj.mvpdemo.contract.PoetryListContract;
import com.example.lsj.mvpdemo.presenter.PoetryListPresenter;
import com.example.lsj.mvpdemo.utils.DataSet;
import com.example.lsj.mvpdemo.view.fragment.PoetryWorksFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class PoetryListActivity extends BaseActivity<PoetryListPresenter> implements PoetryListContract.View {

    private ViewPager mViewPager;
    private CommonTabLayout mTab;
    private TextView name;
    private ImageView img;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ClassificationBean classificationBean;

    @Override
    protected PoetryListPresenter createPresenter() {
        return new PoetryListPresenter();
    }

    @Override
    protected void bindinLayout() {
        mViewPager = findViewById(R.id.poetry_list_vp);
        mTab = findViewById(R.id.poetry_list_tab);
        name = findViewById(R.id.p_list_name);
        img = findViewById(R.id.p_list_img);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_list;
    }

    @Override
    protected void init() {
        classificationBean = (ClassificationBean) DataSet.getObjectData("classification");
        name.setText(classificationBean.getName());
        img.setImageResource(classificationBean.getImgId());

        initLayout();
    }


    private void initLayout(){
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

        //滑动、点击切换页面
        mTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //滑动、点击切换页面
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
