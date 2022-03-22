package com.zzti.school.mapper;

import com.zzti.school.entity.Node;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface NodeMapper {


    //存储新提交的论坛帖子
    @Insert("insert into topic (topic_id,topic_name,topic_content,topic_time,user_id)" +
            "values ((select max_id from(select max(cast(topic_id as signed))+1 as max_id from topic) as m),#{topic_name},#{topic_content},#{topic_time},#{user_id})")
    void addNode(@Param("topic_name")String topic_name , @Param("topic_content")String topic_content, @Param("topic_time")String topic_time,@Param("user_id")String user_id);

    //获取所有帖子信息
    @Select("select * from topic order by cast(topic_id as signed)")
    List<Node> getAllNodes();

    //根据搜索内容查找相关话题
    @Select("select * from topic where instr(topic_name,#{topic_name})")
    List<Node> findNodesByContent(@Param("topic_name") String topic_name);

    //根据帖子ID查找帖子
    @Select("select * from topic where topic_id = #{node_id}")
    Node findNodeByID(@Param("node_id") String node_id);

    //根据用户ID返回所有帖子
    @Select("select * from topic where user_id = #{user_id} order by cast(topic_id as signed)")
    List<Node> getAllNodesByUID(@Param("user_id") String user_id);

}

