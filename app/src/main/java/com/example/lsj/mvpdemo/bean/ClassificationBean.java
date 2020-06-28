package com.example.lsj.mvpdemo.bean;

public class ClassificationBean {

    private String id;
    private int imgId;
    private String name;
    private String img;
    private String type;

    public ClassificationBean(String id, String name, String img, String type) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
    }

    public ClassificationBean(int imgId, String name) {
        this.imgId = imgId;
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassificationBean{" +
                "id='" + id + '\'' +
                ", imgId=" + imgId +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
