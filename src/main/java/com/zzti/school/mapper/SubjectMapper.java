package com.zzti.school.mapper;

import com.zzti.school.entity.Subject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SubjectMapper {
    //根据问卷ID获取所有题目
    @Select("select * from questionnaire_topic where questionnaire_id = #{questionnaire_id}")
    List<Subject> getAllSubjectsByID(@Param("questionnaire_id")String questionnaire_id);

    //根据选项和问卷ID获取Subject列表（选项可能存在重复现象）
    @Select("select * from questionnaire_topic where answer_a = #{option} or answer_b = #{option} or answer_c = #{option} or answer_d = #{option} or answer_e = #{option} or answer_f = #{option} or answer_g = #{option} and  questionnaire_id = #{questionnaire_id} order by cast(question_number as signed)")
    List<Subject> getSubjectByOption(@Param("option") String option,@Param("questionnaire_id")String questionnaire_id);

    //根据问卷ID获取最大题号，即获取问卷题目数量
    @Select("select (select max_number from(select max(cast(question_number as signed)) as max_number from questionnaire_topic where questionnaire_id = #{questionnaire_id}) as m) from questionnaire_topic limit 1")
    String getMax_number(@Param("questionnaire_id") String questionnaire_id);

    //根据问卷ID获取多选题列表
    @Select("select * from questionnaire_topic where questionnaire_id = #{questionnaire_id} and question_type = '2'")
    List<Subject> getCValueByID(@Param("questionnaire_id") String questionnaire_id);

    //根据问卷ID获取单选题列表
    @Select("select * from questionnaire_topic where questionnaire_id = #{questionnaire_id} and question_type = '1'")
    List<Subject> getValueByID(@Param("questionnaire_id") String questionnaire_id);

    //根据问卷ID和题号返回题目ID
    @Select("select subject_id from questionnaire_topic where questionnaire_id = #{questionnaire_id} and question_number = #{question_number}")
    String getSubjectID(@Param("questionnaire_id") String questionnaire_id, @Param("question_number") String question_number);

    //添加问卷ID，题号，提交人ID
    @Insert("insert into questionnaire_answer (questionnaire_id,question_number,user_id,subject_id) values (#{questionnaire_id},#{question_number},#{user_id},#{subject_id})")
    void addAnswer(@Param("questionnaire_id") String questionnaire_id, @Param("question_number") String question_number, @Param("user_id") String user_id,@Param("subject_id")String subject_id);

    //修改选项字段a
    @Update("update questionnaire_answer set answer_a = #{answer_a} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_a(@Param("answer_a")String answer_a,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段b
    @Update("update questionnaire_answer set answer_b = #{answer_b} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_b(@Param("answer_b")String answer_b,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段c
    @Update("update questionnaire_answer set answer_c = #{answer_c} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_c(@Param("answer_c")String answer_c,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段d
    @Update("update questionnaire_answer set answer_d = #{answer_d} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_d(@Param("answer_d")String answer_d,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段e
    @Update("update questionnaire_answer set answer_e = #{answer_e} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_e(@Param("answer_e")String answer_e,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段f
    @Update("update questionnaire_answer set answer_f = #{answer_f} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_f(@Param("answer_f")String answer_f,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //修改选项字段g
    @Update("update questionnaire_answer set answer_g = #{answer_g} where questionnaire_id = #{questionnaire_id} and question_number = #{question_number} and user_id = #{user_id}")
    void updateAnswer_g(@Param("answer_g")String answer_g,@Param("questionnaire_id")String questionnaire_id,@Param("question_number")String question_number,@Param("user_id")String user_id);

    //根据用户ID和问卷ID判断用户是否已经填写该问卷
    @Select("select questionnaire_id from questionnaire_answer where questionnaire_id = #{questionnaire_id} and user_id = #{user_id} limit 1")
    String getAnswer(@Param("questionnaire_id") String questionnaire_id, @Param("user_id") String user_id);

}
