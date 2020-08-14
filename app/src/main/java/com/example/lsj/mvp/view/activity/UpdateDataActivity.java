package com.example.lsj.mvp.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
    String id;

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
        id = intent.getStringExtra("id");
        if (context != null){
            data.setText(context);
        }
        if (field.equals("age")){
            showAgeDialog();
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
                    mPresenter.updateData(field, context, id);
                }
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private void showAgeDialog() {
        final String[] items = { "男","女"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(UpdateDataActivity.this);
        listDialog.setTitle("性别选择");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.setText(items[which]);
            }
        });
        listDialog.show();
    }

    @Override
    public void updateDateSuccess() {
        Toast.makeText(this, "更改成功", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void updateDateFail() {
        Toast.makeText(this, "更改失败", Toast.LENGTH_LONG).show();
    }
}
