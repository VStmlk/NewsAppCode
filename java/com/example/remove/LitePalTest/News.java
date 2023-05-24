package com.example.remove.LitePalTest;

import org.litepal.crud.LitePalSupport;

public class News extends LitePalSupport {
    private int id;
    private String title;
    private String context;
    private String newsdate;
    private String from;
    private String type;
    private String image1;
    private String image2;
    private String image3;

//    public News(String title, String context, String newsdate, String from, String image1, String image2, String image3) {
//        this.title = title;
//        this.context = context;
//        this.newsdate = newsdate;
//        this.from = from;
//        this.image1 = image1;
//        this.image2 = image2;
//        this.image3 = image3;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(String newsdate) {
        this.newsdate = newsdate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
