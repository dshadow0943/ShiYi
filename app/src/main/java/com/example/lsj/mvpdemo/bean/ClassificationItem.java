package com.example.lsj.mvpdemo.bean;

import java.util.ArrayList;
import java.util.List;

public class ClassificationItem {

    private String name;
    private List<ClassificationBean> cfts = new ArrayList<>();

    public ClassificationItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassificationBean> getCfts() {
        return cfts;
    }

    public void setCfts(List<ClassificationBean> cfts) {
        this.cfts = cfts;
    }
}
