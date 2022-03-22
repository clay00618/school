package com.zzti.school.entity;

public class Activity {
    private String activity_id;
    private String user_id;
    private String activity_name;
    private String activity_register_start_time;
    private String activity_register_end_time;
    private String activity_type;
    private String activity_address;
    //活动需要的人数
    private String activity_require;
    private String activity_time;
    //活动说明
    private String activity_explain;


    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_register_start_time() {
        return activity_register_start_time;
    }

    public void setActivity_register_start_time(String activity_register_start_time) {
        this.activity_register_start_time = activity_register_start_time;
    }

    public String getActivity_register_end_time() {
        return activity_register_end_time;
    }

    public void setActivity_register_end_time(String activity_register_end_time) {
        this.activity_register_end_time = activity_register_end_time;
    }

    public String getActivity_address() {
        return activity_address;
    }

    public void setActivity_address(String activity_address) {
        this.activity_address = activity_address;
    }

    public String getActivity_explain() {
        return activity_explain;
    }

    public void setActivity_explain(String activity_explain) {
        this.activity_explain = activity_explain;
    }

    public String getActivity_require() {
        return activity_require;
    }

    public void setActivity_require(String activity_require) {
        this.activity_require = activity_require;
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }

}
