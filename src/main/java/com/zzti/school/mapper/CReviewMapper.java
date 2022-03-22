package com.zzti.school.mapper;

import com.zzti.school.entity.CReview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface CReviewMapper {

    //添加回复
    @Insert("insert into confession_review (confession_id,user_id,confession_review_time,confession_review_content,confession_review_id) values (#{confession_id},#{user_id}" +
            ",#{confession_review_time},#{confession_review_content},(select max_id from (select max(cast(confession_review_id as signed))+1 as max_id from confession_review) as m))")
    void addCReview(@Param("confession_id") String confession_id, @Param("user_id") String user_id, @Param("confession_review_time") String confession_review_time, @Param("confession_review_content") String confession_review_content);

    //根据表白帖子ID获取所有表白回复帖
    @Select("select * from confession_review where confession_id = #{confession_id} order by cast(confession_review_id as signed)")
    List<CReview> getAllCReview(@Param("confession_id") String confession_id);
}
