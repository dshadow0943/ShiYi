package com.example.lsj.mvp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.lsj.mvp.R;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.base.BaseActivity;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.contract.AccountSettingContract;
import com.example.lsj.mvp.presenter.AccountSettingPresenter;
import com.example.lsj.mvp.utils.DataSet;
import com.example.lsj.mvp.utils.FileUtil;

import java.io.File;

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

    private Uri photoUri;
    private Uri corpUri;

    public static final int UPDATE_ACTIVITY = 1;
    public static final int TACK_PHOTO = 2;
    public static final int PICK_PHOTO = 3;
    private static final int CROP_REQUEST_CODE = 4;
    private static final String FILE_PROVIDER_AUTHORITY = "com.example.lsj.mvp.accountsetting.fileprovider";

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
                .load(Api.API+ user.getAvatar())
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
                showImageDialog();
                break;
            case R.id.account_name_set:
                intent.putExtra("field", "name");
                intent.putExtra("context", user.getName());
                startActivityForResult(intent, UPDATE_ACTIVITY);
                break;
            case R.id.account_age_set:
                intent.putExtra("field", "age");
                intent.putExtra("context", user.getAge());
                startActivityForResult(intent, UPDATE_ACTIVITY);
                break;
            case R.id.account_info_set:
                intent.putExtra("field", "info");
                intent.putExtra("context",  user.getInfo());
                startActivityForResult(intent, UPDATE_ACTIVITY);
                break;
            case R.id.account_change_password:
                intent.putExtra("field", "pwd");
                intent.putExtra("context",  "");
                startActivityForResult(intent, UPDATE_ACTIVITY);
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    /*申请权限的回调*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "onRequestPermissionsResult: permission granted");
        } else {
            Log.i("TAG", "onRequestPermissionsResult: permission denied");
            Toast.makeText(this, "You Denied Permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult: " + resultCode);
        switch (requestCode) {
            // 调用相机后返回
            case TACK_PHOTO:
                if (resultCode == RESULT_OK){
                    crop(photoUri);
                }else {
                    Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show();
                }

                break;
            //调用相册后返回
            case PICK_PHOTO:
                if (data == null){
                    return;
                }
                if (resultCode == RESULT_OK){

                    String uriPath;
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        uriPath = FileUtil.handleImageOnKitKat(data, this);//4.4之后图片解析
                        uri = FileProvider.getUriForFile(AccountSettingActivity.this, FILE_PROVIDER_AUTHORITY, new File(uriPath));
                    } else {
                        uriPath = FileUtil.handleImageBeforeKitKat(data, this);//4.4之前图片解析
                        uri = Uri.fromFile(new File(uriPath));
                    }
                    crop(uri);
                }else {
                    Toast.makeText(this, "打开图库失败", Toast.LENGTH_SHORT).show();
                }
                break;

            //调用剪裁后返回
            case CROP_REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    Bitmap bitmap = FileUtil.getBitmapFromUri(corpUri, this);
                    if (bitmap != null) {
                        iAvatar.setImageBitmap(bitmap);
                    }
//                    mPresenter.uploadAvatar(corpUri.getPath()); // 把图片上传到服务器
                }else {
                    Toast.makeText(this, "截图失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case UPDATE_ACTIVITY:
                init(); //重新加载界面
        }
    }

    @Override
    public void getUserDataSuccess(UserBean user) {
        this.user = user;
        display();
    }

    /*申请读取存储的权限*/
    private void showImageDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 267);
            }
        }

        final String[] items = { "拍照","从相册选取", "取消"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(AccountSettingActivity.this);
        listDialog.setTitle("图片选取");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        takePhoto();
                        break;
                    case 1:
                        pickPhoto();
                        break;
                    default:
                        break;
                }
            }
        });
        listDialog.show();
    }

    /**
     * 拍照获取图片
     */
    public void takePhoto(){
        File tempFile = getmCropImageFile();
        //跳转到调用系统相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            photoUri = FileProvider.getUriForFile(AccountSettingActivity.this, FILE_PROVIDER_AUTHORITY, tempFile);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            photoUri = Uri.fromFile(tempFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, TACK_PHOTO);
    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO);
    }

    /**
     * 裁剪图片
     */
    private void crop(Uri uri) {
        corpUri = Uri.fromFile(getmCropImageFile());
        Intent intent = new Intent("com.android.camera.action.CROP");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 270);
        intent.putExtra("outputY", 270);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, corpUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    //获取图片保存地址
    private File getmCropImageFile(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            return file;
        }
        return null;
    }
}
