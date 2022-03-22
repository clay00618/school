package com.zzti.school.service;

import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    //获取所有问卷
    PageInfo<Questionnaire> getAllQuestionnaires(int pageNum, int pageSize);

    //根据问卷名称获取问卷
    List<Questionnaire> findQuestionnaireByName(String questionnaire_name);

    //根据问卷名称获取问卷ID
    String findIDByTitle(String questionnaire_name);
}
