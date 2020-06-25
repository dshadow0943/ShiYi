package com.example.lsj.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lsj.mvpdemo.R;
import com.example.lsj.mvpdemo.view.activity.PoetryListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        startActivity(new Intent(MainActivity.this, PoetryListActivity.class));

    }
}
