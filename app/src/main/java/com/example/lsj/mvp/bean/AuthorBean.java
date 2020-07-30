package com.example.lsj.mvp.bean;

public class AuthorBean {

    private String id;
    private String name;
    private String dynasty;
    private String summary;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "AuthorBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
