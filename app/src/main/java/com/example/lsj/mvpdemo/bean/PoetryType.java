package com.example.lsj.mvpdemo.bean;

public class PoetryType {

    private String name;
    private String imgUrl;
    private String info;

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
}
