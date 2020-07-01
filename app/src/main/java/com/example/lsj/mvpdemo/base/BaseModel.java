package com.example.lsj.mvpdemo.base;

import com.example.lsj.mvpdemo.api.PoetryApi;
import com.example.lsj.mvpdemo.model.IModel;

public class BaseModel implements IModel {

    protected final static PoetryApi poetryApi = PoetryApi.getInstance();

}
