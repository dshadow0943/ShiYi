package com.example.lsj.mvpdemo.bean;

import java.util.List;

public class PoetryBean {

    private String id;
    private String name;
    private String dynasty;
    private String authorName;

//    private List<LabelDao> labels;
    private List<VerseBean> verses;
//    private List<AppreciationDao> appreciations;
    private List<VerseBean> classics;
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

    public List<VerseBean> getVerses() {
        return verses;
    }

    public void setVerses(List<VerseBean> verses) {
        this.verses = verses;
    }

    public List<VerseBean> getClassics() {
        return classics;
    }

    public void setClassics(List<VerseBean> classics) {
        this.classics = classics;
    }

}
