package com.example.lsj.mvpdemo.api;

import com.example.lsj.mvpdemo.bean.ClassificationItem;

import java.util.List;
import io.reactivex.Observable;

import retrofit2.http.GET;

public interface PoetryApiService {

    //获取分类数据
    @GET("category/shows")
    Observable<List<ClassificationItem>> getClassificationItem();

}
