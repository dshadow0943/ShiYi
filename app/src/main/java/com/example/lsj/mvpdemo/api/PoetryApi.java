package com.example.lsj.mvpdemo.api;

import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoetryApi {

    public static PoetryApi getInstance(){
        return new PoetryApi();
    }

    private PoetryApiService poetryApiService;

    public PoetryApi(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(Api.API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitUtil.getOkHttpClient())
                .build();
        poetryApiService = retrofit.create(PoetryApiService.class);
    }

    public Observable<List<ClassificationItem>> getClassificationItem(){
        return poetryApiService.getClassificationItem();
    }

}
