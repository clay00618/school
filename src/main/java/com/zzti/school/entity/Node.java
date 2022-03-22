package com.zzti.school.entity;

public class Node {
    private String topic_id;
    private String topic_name;
    //topic_picture没用
    private String topic_picture;
    private String topic_content;
    private String topic_time;
    private String user_id;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getTopic_time() {
        return topic_time;
    }

    public void setTopic_time(String topic_time) {
        this.topic_time = topic_time;
    }

}
