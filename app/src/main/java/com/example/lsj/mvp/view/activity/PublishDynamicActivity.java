package com.example.lsj.mvp.view.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.DynamicBean;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.PublishDynamicContract;
import com.example.lsj.mvp.presenter.PublishDynamicPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.R;

public class PublishDynamicActivity extends BaseActivity<PublishDynamicPresenter> implements PublishDynamicContract.View, View.OnClickListener {

    TextView tPub;
    EditText eText;
    RelativeLayout rBack;

    @Override
    protected PublishDynamicPresenter createPresenter() {
        return new PublishDynamicPresenter();
    }

    @Override
    protected void bindinLayout() {
        tPub = findViewById(R.id.publish_pub);
        eText = findViewById(R.id.publish_text);
        rBack = findViewById(R.id.back);

        tPub.setOnClickListener(this);
        rBack.setOnClickListener(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_publish_dynamic;
    }

    @Override
    protected void init() {

    }

    private void submitDynamic(){
        UserBean user = DataSet.getUser();
        if (user == null){
            Toast.makeText(this, "你当前不在登录状态，请登录后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        String text = eText.getText().toString();
        if (text.equals("")){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (text.length() > 100){
            Toast.makeText(this, "长度超过限制", Toast.LENGTH_SHORT).show();
            return;
        }
        DynamicBean dynamic = new DynamicBean();
        dynamic.setUserId(user.getId());
        dynamic.setContext(text);
        mPresenter.submitDynamic(dynamic);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publish_pub:
                submitDynamic();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void publishSuccess() {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
