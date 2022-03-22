package com.zzti.school.mapper;

import com.zzti.school.entity.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper {

    //获取所有活动
    @Select("select * from campus_activity order by cast(activity_id as signed)")
    List<Activity> getAllActivities();

    //存储活动
    @Insert("insert into campus_activity (activity_id,user_id,activity_name,activity_register_start_time,activity_register_end_time,activity_address,activity_require,activity_time,activity_explain) values ((select max_id from(select max(cast(activity_id as signed))+1 as max_id from campus_activity) as m),#{user_id},#{activity_name},#{activity_register_start_time},#{activity_register_end_time},#{activity_address},#{activity_require},#{activity_time},#{activity_explain})")
    void addActivity(@Param("user_id")String user_id,@Param("activity_name")String name,@Param("activity_register_start_time")String start_time,@Param("activity_register_end_time")String end_time,@Param("activity_address")String address,@Param("activity_require")String require,@Param("activity_time")String time,@Param("activity_explain")String explain);

    //根据活动ID获取所有的用户ID
    @Select("select user_id from activity_register where activity_id = #{activity_id}")
    List<String> getAllUIDByAID(@Param("activity_id") String activity_id);

    //根据活动ID获取活动
    @Select("select * from campus_activity where activity_id  = #{activity_id}")
    Activity getActivityByID(@Param("activity_id") String activity_id);

    //存储参加的用户信息
    @Insert("insert into activity_register (user_id,activity_id,register_time) values (#{user_id},#{activity_id},#{register_time})")
    void addRegister(@Param("user_id") String user_id, @Param("activity_id") String activity_id, @Param("register_time") String register_time);

    //根据用户ID和活动ID判断用户是否已经参加活动
    @Select("select register_time from activity_register where user_id = #{user_id} and activity_id = #{activity_id}")
    String getJoin(@Param("user_id") String user_id, @Param("activity_id") String activity_id);
}
