package com.example.lsj.mvp.api;

import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.bean.ClassificationItem;
import com.example.lsj.mvp.bean.UserBean;
import com.example.lsj.mvp.bean.VerseBean;
import com.example.lsj.mvp.utils.RetrofitUtil;

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

    public Observable<List<PoetryWorksBean>> getWorksBeanItem(String label){
        return poetryApiService.getWorksBeanItem(label);
    }

    public Observable<List<VerseBean>> getClassicVerseItem(){
        return poetryApiService.getClassicVerseItem();
    }

    public Observable<PoetryBean> getPoetryById(String id){
        return poetryApiService.getPoetryById(id);
    }

    public Observable<String> savePoetry(PoetryBean poetry){
        return poetryApiService.savePoetry(poetry);
    }

    public Observable<List<PoetryWorksBean>> getWorksBeanItem(){
        return poetryApiService.getWorksBeanItem();
    }

    public Observable<List<AuthorBean>> getAuthorItem(){
        return poetryApiService.getAuthorItem();
    }

    public Observable<List<PoetryWorksBean>> getPoetryByAuthorId(String id){
        return poetryApiService.getPoetryByAuthorId(id);
    }

    public Observable<String> updateUser(String type, String value, String id){
        return poetryApiService.updateUser(type, value, id);
    }

    public Observable<UserBean> getUserDateById(String id){
        return poetryApiService.getUserDateById(id);
    }

    public Observable<UserBean> getUserDateByPhone(String id){
        return poetryApiService.getUserDateByPhone(id);
    }

    public Observable<String> registerUser(UserBean user){
        return poetryApiService.registerUser(user);
    }

}
