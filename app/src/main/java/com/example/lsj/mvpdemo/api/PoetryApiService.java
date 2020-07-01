package com.example.lsj.mvpdemo.api;

import com.example.lsj.mvpdemo.bean.ClassificationItem;
import com.example.lsj.mvpdemo.bean.WorksBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PoetryApiService {

    //获取分类数据
    @GET("category/shows")
    Observable<List<ClassificationItem>> getClassificationItem();

    @POST("poetry/seek")
    Observable<List<WorksBean>> getWorksBeanItem(@Query("label") String label);

}
