package com.example.tommy.lib;
public class Poem {
    public String shortcut;
    public String author;
    public String url;
    public String title;
    public String content;
    public String explanation;
    public String explannationSound;
    public String contentSound;
    public Poem(String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }
    public Poem(){
    }
    @Override
    public String toString() {
        return "Poem [author=" + author + ", url=" + url + ", title=" + title + ", content=" + content
                + ", explanation=" + explanation + ", explannationSound=" + explannationSound + ", contentSound="
                + contentSound + "]";
    }
}

