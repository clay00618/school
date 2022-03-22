package com.zzti.school.entity;

public class Confession {


    private String confession_id;
    private String confession_time;
    private String user_id;
    private String confession_content;
    private String confession_picture;

    public Confession(){}

    public Confession(String confession_id,String confession_time,String user_id,String confession_content){
        this.confession_id = confession_id;
        this.confession_time = confession_time;
        this.confession_content = confession_content;
        this.user_id = user_id;

    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setConfession_id(String confession_id) {
        this.confession_id = confession_id;
    }

    public String getConfession_id() {
        return confession_id;
    }

    public void setConfession_time(String confession_time) {
        this.confession_time = confession_time;
    }

    public String getConfession_time() {
        return confession_time;
    }

    public String getConfession_content() {
        return confession_content;
    }

    public void setConfession_content(String confession_content) {
        this.confession_content = confession_content;
    }


}
