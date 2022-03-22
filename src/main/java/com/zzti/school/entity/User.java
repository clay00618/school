package com.zzti.school.entity;

import com.alibaba.druid.support.monitor.annotation.MTable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;



public class User {


    private String user_id;
    private String user_name;
    private String password;
    private String user_sex;
    private String user_age;
    private String user_student_num;
    private String user_nickname;
    private String user_school;
    private String user_department;
    private String user_class;
    private String user_tel;
    private Integer user_type;
    private String user_join_time;
    private String user_state;

    public User(){}

    public User(String user_name){
        this.user_name = user_name;
    }

    public User(String user_name, String password){
        this.user_name = user_name;
        this.password = password;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_age() {
        return user_age;
    }

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }

    public String getUser_department() {
        return user_department;
    }

    public void setUser_department(String user_department) {
        this.user_department = user_department;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public String getUser_student_num() {
        return user_student_num;
    }

    public void setUser_student_num(String user_student_num) {
        this.user_student_num = user_student_num;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_join_time() {
        return user_join_time;
    }

    public void setUser_join_time(String user_join_time) {
        this.user_join_time = user_join_time;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_type() {
        return user_type;
    }
}

