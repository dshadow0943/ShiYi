package com.example.lsj.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.contract.UpdateDataContract;
import com.example.lsj.mvp.presenter.UpdateDataPresenter;
import com.example.lsj.mvpdemo.R;

public class UpdateDataActivity extends BaseActivity<UpdateDataPresenter> implements UpdateDataContract.View, View.OnClickListener {

    EditText data;
    ImageView clear;
    RelativeLayout back;
    Button confirm;

    String context;
    String field;

    @Override
    protected UpdateDataPresenter createPresenter() {
        return new UpdateDataPresenter();
    }

    @Override
    protected void bindinLayout() {
        data = findViewById(R.id.update_data);
        clear = findViewById(R.id.update_clear);
        back = findViewById(R.id.back);
        confirm = findViewById(R.id.update_confirm);

        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_update_data;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        context = intent.getStringExtra("context");
        field = intent.getStringExtra("field");
        if (context != null){
            data.setText(context);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_clear:
                data.setText("");
                break;
            case R.id.update_confirm:
                if (field != null){
                    context = String.valueOf(data.getText());
                    mPresenter.updateData(field, context);
                }
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
