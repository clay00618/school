package com.zzti.school.mapper;

import com.zzti.school.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleMapper {

    //获取所有物品
    @Select("select * from article")
    List<Article> getAllArticles();

    //存储物品信息
    @Insert("insert into article (article_id,article_name,user_id,article_picture_1,article_time,article_condition,article_address,submit_time,article_detail) values ((select max_id from (select max(cast(article_id as signed))+1 as max_id from article) as m),#{article_name},#{user_id},#{article_picture_1},#{article_time},#{article_condition},#{article_address},#{submit_time},#{article_detail})")
    void addArticle(@Param("article_name") String article_name, @Param("user_id") String user_id, @Param("article_picture_1") String article_picture1, @Param("article_time") String article_time, @Param("article_condition") String article_condition, @Param("article_address") String article_address, @Param("submit_time") String submit_time, @Param("article_detail") String article_detail);

    //根据ID返回物品
    @Select("select * from article where article_id = #{article_id}")
    Article getArticleByID(@Param("article_id")String article_id);

    //根据用户ID获取所有失物招领信息
    @Select("select * from article where user_id = #{user_id} order by cast(article_id as signed)")
    List<Article> getAllArticlesByUID(@Param("user_id") String user_id);

    //改变物品状态
    @Update("update article set article_condition = #{article_condition} where article_id = #{article_id}")
    void updateCondition(@Param("article_condition") String article_condition, @Param("article_id") String article_id);
}
