package com.example.lsj.mvp.bean;

import java.util.List;

public class PoetryBean {

    private String id;
    private String name;
    private String dynasty;
    private String authorName;
    private AuthorBean author;

    private List<String> labels;
    private List<VerseBean> verses;
    private List<AppreciationBean> appreciations;
    private List<VerseBean> classics;
    private List<CommentBean> comments;


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

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
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

    public List<AppreciationBean> getAppreciations() {
        return appreciations;
    }

    public void setAppreciations(List<AppreciationBean> appreciations) {
        this.appreciations = appreciations;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "PoetryBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", authorName='" + authorName + '\'' +
                ", author=" + author +
                ", labels=" + labels +
                ", verses=" + verses +
                ", appreciations=" + appreciations +
                ", classics=" + classics +
                ", comments=" + comments +
                '}';
    }
}
