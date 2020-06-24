package com.example.lsj.mvpdemo.bean;

public class ClassificationBean {

    private String id;
    private String imgId;
    private String name;

    public ClassificationBean(String imgId, String name) {
        this.imgId = imgId;
        this.name = name;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
