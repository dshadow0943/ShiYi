package com.example.lsj.mvp.bean;

//赏析
public class AppreciationBean {

    private String series;
    private String poetryId;
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AppreciationBean{" +
                "series='" + series + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
