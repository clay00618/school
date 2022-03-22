package com.zzti.school.entity;

public class CReview {
    private String confession_review_id;
    private String confession_review_order;
    private String confession_id;
    private String user_id;
    private String confession_review_time;
    private String confession_review_content;

    public String getConfession_review_order() {
        return confession_review_order;
    }

    public void setConfession_review_order(String confession_review_order) {
        this.confession_review_order = confession_review_order;
    }

    public void setConfession_id(String confession_id) {
        this.confession_id = confession_id;
    }

    public String getConfession_id() {
        return confession_id;
    }


    public String getConfession_review_time() {
        return confession_review_time;
    }

    public void setConfession_review_time(String confession_review_time) {
        this.confession_review_time = confession_review_time;
    }

    public String getConfession_review_content() {
        return confession_review_content;
    }

    public void setConfession_review_content(String confession_review_content) {
        this.confession_review_content = confession_review_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getConfession_review_id() {
        return confession_review_id;
    }

    public void setConfession_review_id(String confession_review_id) {
        this.confession_review_id = confession_review_id;
    }
}
