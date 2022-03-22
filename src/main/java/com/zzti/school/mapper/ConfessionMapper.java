package com.zzti.school.mapper;

import com.zzti.school.entity.Confession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConfessionMapper {

    //添加表白帖

    @Insert("insert into confession (confession_id,confession_time,user_id,confession_content) values ((select max_id from (select max(cast(confession_id as signed))+1 as max_id from confession) as m),#{confession_time},#{user_id},#{confession_content})")
    void addConfession(@Param("confession_time") String confession_time,@Param("user_id")String user_id,@Param("confession_content")String confession_content);

    //获取所有表白贴
    @Select("select * from confession order by cast(confession_id as signed)")
    List<Confession> getAllConfessions();

    //根据帖子ID查找帖子
    @Select("select * from confession where confession_id = #{confession_id}")
    Confession getConfessionByID(@Param("confession_id") String confession_id);

    //根据用户ID返回所有表白帖
    @Select("select * from confession where user_id = #{user_id} order by cast(confession_id as signed)")
    List<Confession> getAllConfessionsByUID(String user_id);

}
