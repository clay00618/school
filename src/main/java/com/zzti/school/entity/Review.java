package com.zzti.school.entity;

public class Review {

    private String topic_review_id;
    private String user_id;
    private String topic_review_time;
    private String topic_review_content;
    private String topic_id;

    public void setTopic_review_id(String topic_review_id) {
        this.topic_review_id = topic_review_id;
    }

    public String getTopic_review_id() {
        return topic_review_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTopic_review_time(String topic_review_time) {
        this.topic_review_time = topic_review_time;
    }

    public String getTopic_review_time() {
        return topic_review_time;
    }

    public void setTopic_review_content(String topic_review_content) {
        this.topic_review_content = topic_review_content;
    }

    public String getTopic_review_content() {
        return topic_review_content;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_id() {
        return topic_id;
    }
}
