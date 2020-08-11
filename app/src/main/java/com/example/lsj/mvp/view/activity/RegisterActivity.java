package com.example.lsj.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.contract.RegisterContract;
import com.example.lsj.mvp.presenter.RegisterPresenter;
import com.example.lsj.mvpdemo.R;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View, View.OnClickListener {

    private EditText ePhone;
    private EditText eName;
    private EditText eAge;
    private EditText ePwd;
    private EditText ePwd2;
    private EditText eCode;

    private RelativeLayout rBack;
    private Button bReg;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void bindinLayout() {

        ePhone = findViewById(R.id.register_phone);
        eName = findViewById(R.id.register_name);
        eAge = findViewById(R.id.register_age);
        ePwd = findViewById(R.id.register_pwd);
        ePwd2 = findViewById(R.id.register_pwd2);
        eCode = findViewById(R.id.register_code);

        rBack = findViewById(R.id.back);
        bReg = findViewById(R.id.register_reg);

        rBack.setOnClickListener(this);
        bReg.setOnClickListener(this);

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {

    }
}
