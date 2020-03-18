package com.example.tommy;
public class Data {
    public boolean flag = false;
    public String title = "title";
    public String type = "type";
    public String poem_title = "title";
    public String poem_url = "url";
    public String poem_author = "author";
    public String poem_shortcut = "shortcut";
    public String poem_content = "";
    public String poem_cotent_sound = "";
    public String poem_explan = "poem_explan";
    public String poem_zhushi = "";
    public String poem_yiwen = "";
    public String poem_shanxi = "";
    public String poem_author_explan = "";
    public String date = "";
    public String lunar = "";
    public Data(String poem_title, String poem_author, String poem_shortcut) {
        this.poem_title = poem_title;
        this.poem_author = poem_author;
        this.poem_shortcut = poem_shortcut;
    }
    public Data() {
    }
    public Data(String date, String lunar, String poem_title, String poem_author, String poem_shortcut, String poem_content) {
        this.date = date;
        this.lunar = lunar;
        this.poem_title = poem_title;
        this.poem_shortcut = poem_shortcut;
        this.poem_author = poem_author;
        this.poem_content = poem_content;
    }
    public Data(String poem_title, String poem_author, String poem_shortcut, String poem_url) {
        this.poem_title = poem_title;
        this.poem_author = poem_author;
        this.poem_shortcut = poem_shortcut;
        this.poem_url = poem_url;
    }
    public Data(String title, String type) {
        this.title = title;
        this.type = type;
    }
    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", poem_title='" + poem_title + '\'' +
                ", poem_url='" + poem_url + '\'' +
                ", poem_author='" + poem_author + '\'' +
                ", poem_shortcut='" + poem_shortcut + '\'' +
                ", poem_content='" + poem_content + '\'' +
                ", poem_explan='" + poem_explan + '\'' +
                '}';
    }
    public Data setFlagTrue() {
        flag = true;
        return this;
    }
}
