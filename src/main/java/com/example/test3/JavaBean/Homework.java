package com.example.test3.JavaBean;

import cn.bmob.v3.BmobObject;

public class Homework extends BmobObject {
    private String title;
    private User author;
    private String describe;
    private String subject;
    private String beginTime;
    private String ddl;
    private String authorName;

    public Homework(/*String title, String describe, String subject, String beginTime, String ddl, User author*/) {
        /*this.title = title;
        this.describe = describe;
        this.subject = subject;
        this.beginTime = beginTime;
        this.ddl = ddl;
        this.author = author;*/
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
