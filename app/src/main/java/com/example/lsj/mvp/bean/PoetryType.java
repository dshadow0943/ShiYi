package com.example.lsj.mvp.bean;

import java.util.List;

public class PoetryType {

    private String name;
    private String imgUrl;
    private String info;
    private List<PoetryWorksBean> poetrys;

    public PoetryType(String name, String imgUrl, String info) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<PoetryWorksBean> getPoetrys() {
        return poetrys;
    }

    public void setPoetrys(List<PoetryWorksBean> poetrys) {
        this.poetrys = poetrys;
    }
}
