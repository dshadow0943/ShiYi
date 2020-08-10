package com.example.lsj.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.contract.LoginContract;
import com.example.lsj.mvp.presenter.LoginPresenter;
import com.example.lsj.mvpdemo.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private EditText ePhone;
    private EditText ePwd;
    private Button bLog;

    private RelativeLayout back;
    private TextView tRegister;
    private LinearLayout lRegister;


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void bindinLayout() {
        ePhone = findViewById(R.id.login_id);
        ePwd = findViewById(R.id.login_pwd);
        bLog = findViewById(R.id.login_log);
        tRegister = findViewById(R.id.login_register);
        lRegister = findViewById(R.id.login_register_top);
        back = findViewById(R.id.back);

        bLog.setOnClickListener(this);
        tRegister.setOnClickListener(this);
        lRegister.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_log:
                break;
            case R.id.login_register:
                break;
            case R.id.login_register_top:
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
