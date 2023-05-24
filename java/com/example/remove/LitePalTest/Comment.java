package com.example.remove.LitePalTest;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class Comment extends LitePalSupport {
    private int id;
    private int newsid;
    private int userid;
    private String username;
    private String content;
    private String createTime;

//    public Comment(int newsid, int userid, String username, String content, long createTime, List<Comment> replyList) {
//        this.newsid = newsid;
//        this.userid = userid;
//        this.username = username;
//        this.content = content;
//        this.createTime = createTime;
//        this.replyList = replyList;
//    }

    public Comment() {

    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}