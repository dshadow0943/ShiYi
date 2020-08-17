package com.example.lsj.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lsj.mvp.R;

public class FollowedDynamicsFragment extends Fragment {

    public static FollowedDynamicsFragment newInstance() {
        return new FollowedDynamicsFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followed_dynamics, container, false);
    }
}
