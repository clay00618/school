package com.zzti.school.mapper;

import com.zzti.school.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {

    //添加建议和举报信息
    @Insert("insert into jubao (user_id,type,j_time,j_content,jubao_id) values (#{user_id},#{type},#{j_time},#{j_content},(select max_id from (select max(cast(jubao_id as signed))+1 as max_id from jubao) as m))")
    void addReport(@Param("user_id") String user_id, @Param("type") String type, @Param("j_time") String j_time, @Param("j_content") String j_content);

    //根据用户ID获取所有举报和建议信息
    @Select("select * from jubao where user_id = #{user_id} order by cast(jubao_id as signed)")
    List<Report> getAllReportsByUID(@Param("user_id") String user_id);
}
