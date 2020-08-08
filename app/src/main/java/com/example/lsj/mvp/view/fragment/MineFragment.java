package com.example.lsj.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.base.BaseFragment;
import com.example.lsj.mvp.bean.MineBean;
import com.example.lsj.mvp.contract.MineContract;
import com.example.lsj.mvp.presenter.MinePresenter;
import com.example.lsj.mvp.view.activity.SiteActivity;
import com.example.lsj.mvpdemo.R;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View, View.OnClickListener {

    private MineBean mineBean = new MineBean();

    private ImageView iAvatar;
    private ImageView iSite;
    private TextView tName;
    private TextView tId;
    private TextView tInfo;
    private TextView tAttentionNum;
    private TextView tFansNum;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void bindinLayout() {
        iAvatar = view.findViewById(R.id.mine_avatar);
        iSite = view.findViewById(R.id.mine_site);
        tName = view.findViewById(R.id.mine_name);
        tId = view.findViewById(R.id.mine_id);
        tInfo = view.findViewById(R.id.mine_info);
        tAttentionNum = view.findViewById(R.id.mine_attention_num);
        tFansNum = view.findViewById(R.id.mine_fans_num);

        iSite.setOnClickListener(this);

        display();
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void init() {

    }

    private void display(){
        Glide.with(this)
                .load(Api.API+mineBean.getAvatarUrl())
                .error(R.mipmap.ic_default)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iAvatar);

        tName.setText(mineBean.getName());
        tId.setText("ID: " + mineBean.getId());
        tInfo.setText(mineBean.getInfo());
        tAttentionNum.setText(String.valueOf(mineBean.getAttentionNum()));
        tFansNum.setText(String.valueOf(mineBean.getFansNum()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_site:
                startActivity(new Intent(getContext(), SiteActivity.class));
                break;

            default:
                break;
        }
    }
}
