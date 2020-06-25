package com.example.lsj.mvpdemo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lsj.mvpdemo.R;

public class PoetryWorksFragment extends Fragment {

    public static PoetryWorksFragment newInstance() {
        return new PoetryWorksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_poetry_works_list, container, false);
    }
}
