package com.zzti.school.entity;

public class News {
    private String news_id;
    private String user_id;
    private String news_time;
    private String news_content;
    private String news_title;

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public String getNews_time() {
        return news_time;
    }

    public void setNews_time(String news_time) {
        this.news_time = news_time;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }
}
