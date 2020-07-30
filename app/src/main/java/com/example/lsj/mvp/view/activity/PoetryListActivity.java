package com.example.lsj.mvp.view.activity;

import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lsj.mvp.adapter.PoetryListPagerAdapter;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.PoetryListTabEntityBean;
import com.example.lsj.mvp.bean.PoetryType;
import com.example.lsj.mvp.contract.PoetryListContract;
import com.example.lsj.mvp.presenter.PoetryListPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.utils.Jump;
import com.example.lsj.mvp.view.fragment.PoetryWorksFragment;
import com.example.lsj.mvpdemo.R;
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
    private PoetryType poetryType;
    private SearchView findBox;
    private String findText;

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

        findBox = findViewById(R.id.find_box);

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
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_poetry_list;
    }

    @Override
    protected void init() {
        poetryType = (PoetryType) DataSet.getObjectData("poetryType");
        if (poetryType == null){
            return;
        }
        name.setText(poetryType.getName());
        Glide.with(this)
                .load(poetryType.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.mipmap.ic_default)
                .into(img);

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
