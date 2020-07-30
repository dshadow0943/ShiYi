package com.example.lsj.mvp.bean;

public class ReptileItemBean {

    private String title;
    private String url;
    private String dynasty;
    private String author;
    private String verse;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    @Override
    public String toString() {
        return "ReptileItemBean{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", author='" + author + '\'' +
                ", verse='" + verse + '\'' +
                '}';
    }
}
