package com.example.remove.LitePalTest;

import org.litepal.crud.LitePalSupport;

public class Collect_sum extends LitePalSupport {
    private int id;
    private int newsid;
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


}
