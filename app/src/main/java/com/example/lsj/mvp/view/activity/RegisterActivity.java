package com.example.lsj.mvp.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.RegisterContract;
import com.example.lsj.mvp.presenter.RegisterPresenter;
import com.example.lsj.mvp.R;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View, View.OnClickListener {

    private EditText ePhone;
    private EditText eName;
    private TextView eAge;
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

        eAge.setOnClickListener(this);
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

    private void register(){
        UserBean user = new UserBean();
        /**
         * 验证手机号合法性
         */
        String phone = ePhone.getText().toString();
        if (phone.equals("") || phone.length() != 11 || !phone.startsWith("1")){
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
            return;
        }
        user.setPhone(phone);

        /**
         * 验证昵称合法性
         */
        String name = eName.getText().toString();
        if (name.equals("") || name.length() < 1){
            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        user.setName(name);

        /**
         * 验证性别合法性
         */
        String age = eAge.getText().toString();
        if (!age.equals("男") && !age.equals("女")){
            Toast.makeText(this, "性别填写错误，只能为“男” 或 “女”", Toast.LENGTH_LONG).show();
            eAge.setText("");
            return;
        }
        user.setAge(age);

        /**
         * 验证密码合法性
         */
        String pwd = ePwd.getText().toString();
        if (pwd.equals("") || pwd.length() < 6){
            Toast.makeText(this, "密码长度不得小于6位", Toast.LENGTH_LONG).show();
            return;
        }

        String pwd2 = ePwd2.getText().toString();
        if (!pwd2.equals(pwd)){
            Toast.makeText(this, "密码不一致", Toast.LENGTH_LONG).show();
            ePwd2.setText("");
            return;
        }
        user.setPwd(pwd);

        /**
         * 提交至服务器
         */
        mPresenter.registerUser(user);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.register_age:
                showAgeDialog();
                break;
            case R.id.register_reg:
                register();
                break;
        }
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "注册成功，返回登录", Toast.LENGTH_LONG).show();
//        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void registerFail() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();
    }

    private void showAgeDialog() {
        final String[] items = { "男","女"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(RegisterActivity.this);
        listDialog.setTitle("性别选择");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eAge.setText(items[which]);
            }
        });
        listDialog.show();
    }

}
