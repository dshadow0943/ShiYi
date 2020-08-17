package com.example.lsj.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lsj.mvp.adapter.PoetryLibraryPagerApapter;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.MineContract;
import com.example.lsj.mvp.presenter.MinePresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.view.activity.SiteActivity;
import com.example.lsj.mvp.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View, View.OnClickListener {

    private UserBean user;

    private ImageView iAvatar;
    private ImageView iSite;
    private TextView tName;
    private TextView tId;
    private TextView tInfo;
    private TextView tAttentionNum;
    private TextView tFansNum;

    private String[] tText = new String[]{"帖子", "收藏", "历史浏览"};
    private List<Fragment> fragments = new ArrayList<>();
    private SegmentTabLayout mTab;
    private ViewPager mVp;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void bindinLayout() {
        iAvatar = view.findViewById(R.id.mine_avatar);
        iSite = view.findViewById(R.id.mine_site);
        tName = view.findViewById(R.id.mine_name);
        tId = view.findViewById(R.id.mine_id);
        tInfo = view.findViewById(R.id.mine_info);
        tAttentionNum = view.findViewById(R.id.mine_attention_num);
        tFansNum = view.findViewById(R.id.mine_fans_num);

        mTab = view.findViewById(R.id.mine_tab);
        mVp = view.findViewById(R.id.mine_vp);

        iSite.setOnClickListener(this);

    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void init() {
        user = DataSet.getUser();
        if (user == null){
            user = new UserBean();
        }
        display();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void display(){
        Glide.with(this)
                .load(Api.API+ user.getAvatar())
                .error(R.mipmap.ic_default)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iAvatar);
        tName.setText(user.getName());
        tId.setText("ID: " + user.getId());
        tInfo.setText(user.getInfo());
        tAttentionNum.setText(String.valueOf(user.getAttentionNum()));
        tFansNum.setText(String.valueOf(user.getFansNum()));


        WindowManager wm = (WindowManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = iAvatar.getLayoutParams();

        lp.width = (width)/5;
        lp.height = (width)/5;
        iAvatar.setLayoutParams(lp);

        configViews();

    }

    private void configViews() {
        fragments.add(AllDynamicsFragment.newInstance(1));
        fragments.add(FollowedDynamicsFragment.newInstance());
        fragments.add(FollowedDynamicsFragment.newInstance());
        mTab.setTabData(tText);
        mTab.setIndicatorCornerRadius(0);
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
            case R.id.mine_site:
                startActivityForResult(new Intent(getContext(), SiteActivity.class), 1);
                break;

            default:
                break;
        }
    }
}
