package com.zzti.school.mapper;

import com.zzti.school.entity.NReview;
import com.zzti.school.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper {

    //获取所有新闻
    @Select("select * from news order by cast(news_id as signed)")
    List<News> getAllNews();

    //根据新闻ID查找新闻
    @Select("select * from news where news_id = #{news_id}")
    News findNewsByID(@Param("news_id") String news_id);

    //根据新闻ID查找所有回复
    @Select("select * from news_review where news_id = #{news_id}")
    List<NReview> getALLReviews(@Param("news_id") String news_id);

    //添加新闻回复
    @Insert("insert into news_review (news_id,news_review_time,news_review_content,user_id) values (#{news_id},#{time},#{content},#{user_id})")
    void addReview(@Param("news_id") String news_id, @Param("time") String time, @Param("content") String content, @Param("user_id") String user_id);

}
