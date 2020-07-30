package com.example.lsj.mvp.bean;

public class ClassificationBean {

    private String id;
    private int imgId;
    private String name;
    private String imgPath;
    private String typeName;

    public ClassificationBean(String id, String name, String imgPath, String typeName) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.typeName = typeName;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ClassificationBean{" +
                "id='" + id + '\'' +
                ", imgId=" + imgId +
                ", name='" + name + '\'' +
                ", img='" + imgPath + '\'' +
                ", type='" + typeName + '\'' +
                '}';
    }
}
