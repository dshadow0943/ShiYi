package com.example.lsj.mvp.bean;

import java.util.List;

public class VerseBean {

    private String id;
    private String series;
    private String poetryId;
    private String authorId;
    private String authorName;
    private String text;
    private String translation;
    private List<CommentBean> comments;
    private boolean classic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(String poetryId) {
        this.poetryId = poetryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isClassic() {
        return classic;
    }

    public void setClassic(boolean classic) {
        this.classic = classic;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "VerseBean{" +
                "id='" + id + '\'' +
                ", series='" + series + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", text='" + text + '\'' +
                ", translation='" + translation + '\'' +
                ", comments=" + comments +
                ", classic=" + classic +
                '}';
    }
}
