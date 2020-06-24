package com.example.lsj.mvpdemo.view.activity;

import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.base.BaseActivity;
import com.example.lsj.mvpdemo.contract.HomeContract;
import com.example.lsj.mvpdemo.presenter.HomePresenter;
import com.example.lsj.mvpdemo.view.fragment.AppreciateFragment;
import com.example.lsj.mvpdemo.view.fragment.MineFragment;
import com.example.lsj.mvpdemo.view.fragment.PlatformFragment;
import com.example.lsj.mvpdemo.view.fragment.PoetryLibraryFragment;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    private EasyNavigationBar navigationBar;

    private String[] tabText = new String[]{"鉴赏", "论坛", "词库", "我的"};
    //未选中icon
    private int[] normalIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    //选中时icon
    private int[] selectIcon = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected int getViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void bindinLayout() {
        navigationBar = findViewById(R.id.navigationBar);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void init() {
        configViews();
    }

    public void configViews() {
        fragments.add(AppreciateFragment.newInstance());
        fragments.add(PlatformFragment.newInstance());
        fragments.add(PoetryLibraryFragment.newInstance());
        fragments.add(MineFragment.newInstance("", ""));
        navigationBar.titleItems(tabText) //必传  Tab文字集合
                .normalIconItems(normalIcon) //必传  Tab未选中图标集合
                .selectIconItems(selectIcon) //必传  Tab选中图标集合
                .normalTextColor(Color.parseColor("#8a8a8a"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#BA6BC6"))   //Tab选中时字体颜色
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        if (position == 2) {

                        }
                        return false;
                    }
                })
                .fragmentList(fragments) //必传  fragment集合
                .fragmentManager(getSupportFragmentManager()) //必传
                .anim(Anim.ZoomInUp)
                .build();
    }
}
