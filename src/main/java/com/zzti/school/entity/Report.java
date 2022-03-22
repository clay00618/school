package com.zzti.school.entity;

public class Report {
    private String user_id;
    private String type;
    private String j_time;
    private String j_content;
    private String jubao_id;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJ_time() {
        return j_time;
    }

    public void setJ_time(String j_time) {
        this.j_time = j_time;
    }

    public String getJ_content() {
        return j_content;
    }

    public void setJ_content(String j_content) {
        this.j_content = j_content;
    }

    public String getJubao_id() {
        return jubao_id;
    }

    public void setJubao_id(String jubao_id) {
        this.jubao_id = jubao_id;
    }
}
