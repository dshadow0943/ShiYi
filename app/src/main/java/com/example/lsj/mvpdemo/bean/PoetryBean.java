package com.example.lsj.mvpdemo.bean;

import java.util.List;

public class PoetryBean {

    private String id;
    private String name;
    private String dynasty;
    private String authorName;

//    private List<LabelDao> labels;
    private List<WorksBean> verses;
//    private List<AppreciationDao> appreciations;
    private List<WorksBean> classics;
//    private List<CommentDao> commentDaos;


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

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<WorksBean> getVerses() {
        return verses;
    }

    public void setVerses(List<WorksBean> verses) {
        this.verses = verses;
    }

    public List<WorksBean> getClassics() {
        return classics;
    }

    public void setClassics(List<WorksBean> classics) {
        this.classics = classics;
    }
}
