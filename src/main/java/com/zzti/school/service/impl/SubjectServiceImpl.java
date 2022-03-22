package com.zzti.school.service.impl;

import com.zzti.school.entity.Subject;
import com.zzti.school.mapper.SubjectMapper;
import com.zzti.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public   class SubjectServiceImpl implements SubjectService {


    @Autowired
    private SubjectMapper subjectMapper;


    @Override
    public List<Subject> getAllSubjectsByID(String questionnaire_id) {
        return subjectMapper.getAllSubjectsByID(questionnaire_id);
    }

    @Override
    public List<Subject> getSubjectByOption(String option,String questionnaire_id) {
        return subjectMapper.getSubjectByOption(option,questionnaire_id);
    }

    @Override
    public void addAnswer(String questionnaire_id, String question_number, String user_id,String subject_id) {
        subjectMapper.addAnswer(questionnaire_id,question_number,user_id,subject_id);
    }

    @Override
    public String getMax_number(String questionnaire_id) {
        return subjectMapper.getMax_number(questionnaire_id);
    }

    @Override
    public String getSubjectID(String questionnaire_id, String question_number) {
        return subjectMapper.getSubjectID(questionnaire_id,question_number);
    }

    @Override
    public List<Subject> getCValueByID(String questionnaire_id) {
        return subjectMapper.getCValueByID(questionnaire_id);
    }

    @Override
    public List<Subject> getValueByID(String questionnaire_id) {
        return subjectMapper.getValueByID(questionnaire_id);
    }

    @Override
    public void updateAnswer_a(String answer_a, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_a(answer_a,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_b(String answer_b, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_b(answer_b,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_c(String answer_c, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_c(answer_c,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_d(String answer_d, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_d(answer_d,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_e(String answer_e, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_e(answer_e,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_f(String answer_f, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_f(answer_f,questionnaire_id,question_number,user_id);
    }

    @Override
    public void updateAnswer_g(String answer_g, String questionnaire_id, String question_number,String user_id) {
        subjectMapper.updateAnswer_g(answer_g,questionnaire_id,question_number,user_id);
    }

    @Override
    public String getAnswer(String questionnaire_id, String user_id) {
        return subjectMapper.getAnswer(questionnaire_id,user_id);
    }

}
