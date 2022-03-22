package com.zzti.school.service;

import com.zzti.school.entity.Subject;

import java.util.List;

public interface SubjectService {


    //根据问卷ID获取所有题目
    List<Subject> getAllSubjectsByID(String questionnaire_id);

    //根据选项和问卷ID获取Subject对象
    List<Subject> getSubjectByOption(String option,String questionnaire_id);

    //添加问卷ID，题号，提交人ID
    void addAnswer(String questionnaire_id,String question_number,String user_id,String subject_id);

    //根据问卷ID获取最大题号，即获取问卷题目数量
    String getMax_number(String questionnaire_id);

    //根据问卷ID和题号返回题目ID
    String getSubjectID(String questionnaire_id, String question_number);

    //根据问卷ID获取多选题列表
    List<Subject> getCValueByID(String questionnaire_id);

    //根据问卷ID获取单选题列表
    List<Subject> getValueByID(String questionnaire_id);

    //修改选项字段a
    void updateAnswer_a(String answer_a,String questionnaire_id,String question_number,String user_id);

    //修改选项字段b
    void updateAnswer_b(String answer_b,String questionnaire_id,String question_number,String user_id);

    //修改选项字段c
    void updateAnswer_c(String answer_c,String questionnaire_id,String question_number,String user_id);

    //修改选项字段d
    void updateAnswer_d(String answer_d,String questionnaire_id,String question_number,String user_id);

    //修改选项字段e
    void updateAnswer_e(String answer_e,String questionnaire_id,String question_number,String user_id);

    //修改选项字段f
    void updateAnswer_f(String answer_f,String questionnaire_id,String question_number,String user_id);

    //修改选项字段g
    void updateAnswer_g(String answer_g,String questionnaire_id,String question_number,String user_id);

    //根据用户ID和问卷ID判断用户是否已经填写该问卷
    String getAnswer(String questionnaire_id, String user_id);

}
