package com.example.lsj.mvp.utils;

import android.content.Context;
import android.content.Intent;

import com.example.lsj.mvp.view.activity.FindPoetryItemActivity;

public class Jump {

    public static void jump(String findText, Context context){
        if (findText == null || findText.equals("")){
            return;
        }
        Intent intent = new Intent(context, FindPoetryItemActivity.class);
        intent.putExtra("find", findText);
        context.startActivity(intent);
    }

}
