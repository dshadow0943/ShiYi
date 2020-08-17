package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.presenter.IPresenter;
import com.example.lsj.mvp.R;

public class SiteActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout rAccount;
    LinearLayout lSignOut;

    RelativeLayout back;

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void bindinLayout() {
        rAccount = findViewById(R.id.site_account);
        lSignOut = findViewById(R.id.site_sign_out);
        back = findViewById(R.id.back);

        back.setOnClickListener(this);
        rAccount.setOnClickListener(this);
        lSignOut.setOnClickListener(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_site;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.site_account:
                startActivity(new Intent(this, AccountSettingActivity.class));
                break;
            case R.id.site_sign_out:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.back:
                finish();
            default:
                break;
        }
    }
}
