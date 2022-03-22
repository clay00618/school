package com.zzti.school.mapper;

import com.zzti.school.entity.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface ReviewMapper {

    //根据帖子ID返回所有评论
    @Select("select * from topic_review where topic_id = #{node_id} ORDER BY CAST(topic_review_id AS signed)")
    List<Review> getAllReviews(@Param("node_id") String node_id);

    //存储新提交的回复
    @Insert("insert into topic_review (topic_review_id,topic_id,topic_review_time,topic_review_content,user_id) values ((select max_id from (select max(cast(topic_review_id as signed))+1 as max_id from topic_review) as m),#{topic_id},#{topic_review_time},#{topic_review_content},#{user_id})")
    void addReview(@Param("topic_id") String topic_id, @Param("topic_review_time") String topic_review_time, @Param("topic_review_content") String topic_review_content, @Param("user_id") String user_id);

}
