package com.example.lsj.mvp.base;

import com.example.lsj.mvp.api.PoetryApi;
import com.example.lsj.mvp.model.IModel;

public class BaseModel implements IModel {

    protected final static PoetryApi poetryApi = PoetryApi.getInstance();

}
