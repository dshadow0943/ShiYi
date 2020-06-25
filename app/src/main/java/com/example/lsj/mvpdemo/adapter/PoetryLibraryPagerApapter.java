package com.example.lsj.mvpdemo.adapter;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PoetryLibraryPagerApapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> listfragment;
//    private List<String> tlabText;

    public PoetryLibraryPagerApapter(FragmentManager fm, List<Fragment> listfragment, Activity activity) {
        super(fm);
        this.listfragment = listfragment;
        this.context = activity;
//        this.tlabText = tlabText;
    }

    @Override
    public Fragment getItem(int i) {
        return listfragment.get(i);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tlabText.get(position);
//    }
}
