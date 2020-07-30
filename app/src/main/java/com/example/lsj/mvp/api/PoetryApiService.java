package com.example.lsj.mvp.api;

import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.bean.VerseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
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

    @GET("poetry/shows")
    Observable<List<PoetryWorksBean>> getWorksBeanItem();

    @GET("author/shows")
    Observable<List<AuthorBean>> getAuthorItem();

    @POST("poetry/seek")
    Observable<List<PoetryWorksBean>> getPoetryByAuthorId(@Query("authorId") String id);

    @POST("poetry/seek")
    Observable<PoetryBean> getPoetryById(@Query("id") String id);

    @POST("poetry/save")
    Observable<String> savePoetry(@Body PoetryBean poetryBean);

}
