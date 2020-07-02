package com.example.lsj.mvpdemo.api;

import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.bean.PoetryBean;
import com.example.lsj.mvpdemo.bean.PoetryWorksBean;
import com.example.lsj.mvpdemo.bean.VerseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PoetryApiService {

    //获取分类数据
    @GET("category/shows")
    Observable<List<ClassificationItem>> getClassificationItem();

    @GET("verse/classic")
    Observable<List<VerseBean>> getClassicVerseItem();

    @POST("poetry/seek")
    Observable<List<PoetryWorksBean>> getWorksBeanItem(@Query("label") String label);

    @POST("poetry/seek")
    Observable<PoetryBean> getPoetryById(@Query("id") String id);

}
