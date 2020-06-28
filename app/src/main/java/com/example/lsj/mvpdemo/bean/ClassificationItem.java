package com.example.lsj.mvpdemo.bean;

import java.util.List;

public class ClassificationItem {

    private String id;
    private String name;
    private List<ClassificationBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassificationItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassificationBean> getList() {
        return list;
    }

    public void setList(List<ClassificationBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ClassificationItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lsit=" + list.toString() +
                '}';
    }
}
