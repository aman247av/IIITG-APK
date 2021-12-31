package com.jee00.IIITG.Model;

public class Books {
    private String title;
    private String cat;
    private String sub;
    private String link;

    public Books(){}

    public Books(String title, String cat, String sub, String link) {
        this.title = title;
        this.cat = cat;
        this.sub = sub;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
