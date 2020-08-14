package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.LoginContract;
import com.example.lsj.mvp.presenter.LoginPresenter;
import com.example.lsj.mvp.utils.DataSet;
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

    private void login(){

        String phone = ePhone.getText().toString();
        if (phone.equals("") || phone.length() != 11 || !phone.startsWith("1")){
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
            return;
        }
        String pwd = ePwd.getText().toString();
        if (pwd.equals("") || pwd.length() < 6){
            Toast.makeText(this, "密码长度不得小于6位", Toast.LENGTH_LONG).show();
            return;
        }
        mPresenter.loginUser(phone, pwd);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_log:
                login();
                break;
            case R.id.login_register:
            case R.id.login_register_top:
                startActivity(new Intent(this, RegisterActivity.class));
//                finish();
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(UserBean user) {
        DataSet.setUser(user);
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void loginFail(UserBean user) {
        if (user == null || user.getId() == null){
            Toast.makeText(this, "登录失败，该账号尚未注册", Toast.LENGTH_LONG).show();
            ePwd.setText("");
            return;
        }else if (!user.getPwd().equals(ePwd.getText().toString())){
            Toast.makeText(this, "登录失败，密码错误", Toast.LENGTH_LONG).show();
            ePwd.setText("");
            return;
        }else {
            Toast.makeText(this, "登录失败，请输入正确的", Toast.LENGTH_LONG).show();
            ePhone.setText("");
            ePwd.setText("");
        }
    }
}
