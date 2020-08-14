package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.AccountSettingContract;
import com.example.lsj.mvp.presenter.AccountSettingPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvpdemo.R;

public class AccountSettingActivity extends BaseActivity<AccountSettingPresenter> implements AccountSettingContract.View, View.OnClickListener {

    RelativeLayout rAvatar;
    RelativeLayout rName;
    RelativeLayout rAge;
    RelativeLayout rInfo;
    RelativeLayout rPwd;

    ImageView iAvatar;
    TextView tName;
    TextView tAge;
    TextView tInfo;

    RelativeLayout back;

    UserBean user;

    @Override
    protected AccountSettingPresenter createPresenter() {
        return new AccountSettingPresenter();
    }

    @Override
    protected void bindinLayout() {
        rAvatar = findViewById(R.id.account_img_set);
        rName = findViewById(R.id.account_name_set);
        rAge = findViewById(R.id.account_age_set);
        rInfo = findViewById(R.id.account_info_set);
        rPwd = findViewById(R.id.account_change_password);
        iAvatar = findViewById(R.id.account_img);
        tName = findViewById(R.id.account_name);
        tAge = findViewById(R.id.account_age);
        tInfo = findViewById(R.id.account_info);
        back = findViewById(R.id.back);

        rAvatar.setOnClickListener(this);
        rName.setOnClickListener(this);
        rAge.setOnClickListener(this);
        rInfo.setOnClickListener(this);
        rPwd.setOnClickListener(this);
        back.setOnClickListener(this);



    }

    private void display(){
        Glide.with(this)
                .load(Api.API+ user.getAvatarUrl())
                .error(R.mipmap.ic_default)
                .into(iAvatar);
        tName.setText(user.getName());
        tAge.setText(user.getAge());
        tInfo.setText(user.getInfo());
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_account_setting;
    }

    @Override
    protected void init() {
        user = DataSet.getUser();
        if (user == null){
            user = new UserBean();
            display();
        }else {
            mPresenter.getUserData(user.getId());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, UpdateDataActivity.class);
        intent.putExtra("id", user.getId());
        switch (v.getId()){
            case R.id.account_img_set:
                Toast.makeText(this, "该功能暂未开放", Toast.LENGTH_LONG).show();
                break;
            case R.id.account_name_set:
                intent.putExtra("field", "name");
                intent.putExtra("context", user.getName());
                startActivityForResult(intent, 1);
                break;
            case R.id.account_age_set:
                intent.putExtra("field", "age");
                intent.putExtra("context", user.getAge());
                startActivityForResult(intent, 1);
                break;
            case R.id.account_info_set:
                intent.putExtra("field", "info");
                intent.putExtra("context",  user.getInfo());
                startActivityForResult(intent, 1);
                break;
            case R.id.account_change_password:
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            init();
        }
    }

    @Override
    public void getUserDataSuccess(UserBean user) {
        this.user = user;
        display();
    }
}
