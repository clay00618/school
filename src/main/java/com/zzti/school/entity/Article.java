package com.zzti.school.entity;

public class  Article {
    private String article_id;
    private String article_name;
    private String user_id;
    private String article_picture_1;
    private String article_picture_2;
    private String article_picture_3;
    //丢失时间
    private String article_time;
    //1为未认领，2为认领中，3为已认领
    private String article_condition;
    private String article_address;
    //提交时间
    private String submit_time;
    private String user_id_owner;
    private String get_time;
    private String article_detail;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArticle_picture_1() {
        return article_picture_1;
    }

    public void setArticle_picture_1(String article_picture_1) {
        this.article_picture_1 = article_picture_1;
    }

    public String getArticle_picture_2() {
        return article_picture_2;
    }

    public void setArticle_picture_2(String article_picture_2) {
        this.article_picture_2 = article_picture_2;
    }

    public String getArticle_picture_3() {
        return article_picture_3;
    }

    public void setArticle_picture_3(String article_picture_3) {
        this.article_picture_3 = article_picture_3;
    }

    public String getArticle_address() {
        return article_address;
    }

    public void setArticle_address(String article_address) {
        this.article_address = article_address;
    }

    public String getArticle_condition() {
        return article_condition;
    }

    public void setArticle_condition(String article_condition) {
        this.article_condition = article_condition;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getGet_time() {
        return get_time;
    }

    public void setGet_time(String get_time) {
        this.get_time = get_time;
    }

    public String getUser_id_owner() {
        return user_id_owner;
    }

    public void setUser_id_owner(String user_id_owner) {
        this.user_id_owner = user_id_owner;
    }

    public String getArticle_detail() {
        return article_detail;
    }

    public void setArticle_detail(String article_detail) {
        this.article_detail = article_detail;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }
}
