package com.example.lsj.mvp.view.activity;

import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.contract.HomeContract;
import com.example.lsj.mvp.presenter.HomePresenter;
import com.example.lsj.mvp.view.fragment.ClassicVerseFragment;
import com.example.lsj.mvp.view.fragment.MineFragment;
import com.example.lsj.mvp.view.fragment.PlatformFragment;
import com.example.lsj.mvp.view.fragment.PoetryLibraryFragment;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    private EasyNavigationBar navigationBar;

    private String[] tabText = new String[]{"鉴赏", "论坛", "词库", "我的"};
    //未选中icon
    private int[] normalIcon = {R.drawable.ic_home_appreciate_w_28dp, R.drawable.ic_home_platform_w_28dp, R.drawable.ic_home_poetrylibrary_w_28dp, R.drawable.ic_home_mine_w_28dp};
    //选中时icon
    private int[] selectIcon = {R.drawable.ic_home_appreciate_b_28dp, R.drawable.ic_home_platfrom_b_28dp, R.drawable.ic_home_poetrylibrary_b_28dp, R.drawable.ic_home_mine_b_28dp};

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
        fragments.add(ClassicVerseFragment.newInstance());
        fragments.add(PlatformFragment.newInstance());
        fragments.add(PoetryLibraryFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        navigationBar.titleItems(tabText) //必传  Tab文字集合
                .normalIconItems(normalIcon) //必传  Tab未选中图标集合
                .selectIconItems(selectIcon) //必传  Tab选中图标集合
                .normalTextColor(Color.parseColor("#8a8a8a"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#7C99FF"))   //Tab选中时字体颜色
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

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
