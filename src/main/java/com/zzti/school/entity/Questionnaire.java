package com.zzti.school.entity;

public class Questionnaire {
    private String questionnaire_id;
    private String questionnaire_name;
    private String user_id;
    private String questionnaire_start_time;
    private String questionnaire_end_time;
    private String questionnaire_require;
    private String questionnaire_type;


    public String getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(String questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public void setQuestionnaire_name(String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuestionnaire_start_time() {
        return questionnaire_start_time;
    }

    public void setQuestionnaire_start_time(String questionnaire_start_time) {
        this.questionnaire_start_time = questionnaire_start_time;
    }

    public String getQuestionnaire_end_time() {
        return questionnaire_end_time;
    }

    public void setQuestionnaire_end_time(String questionnaire_end_time) {
        this.questionnaire_end_time = questionnaire_end_time;
    }

    public String getQuestionnaire_require() {
        return questionnaire_require;
    }

    public void setQuestionnaire_require(String questionnaire_require) {
        this.questionnaire_require = questionnaire_require;
    }


}
