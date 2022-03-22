package com.zzti.school.mapper;

import com.zzti.school.entity.Questionnaire;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionnaireMapper {

    //获取所有问卷
    @Select("select * from questionnaire order by cast(questionnaire_id as signed)")
    List<Questionnaire> getAllQuestionnaires();

    //根据问卷名称模糊查询问卷
    @Select("select * from questionnaire where instr(questionnaire_name,#{questionnaire_name})")
    List<Questionnaire> findQuestionnaireByName(@Param("questionnaire_name") String questionnaire_name);

    //根据问卷名称获取问卷ID
    @Select("select questionnaire_id from questionnaire where questionnaire_name = #{questionnaire_name}")
    String findIDByTitle (@Param("questionnaire_name")String questionnaire_name);
}
