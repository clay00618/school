package com.zzti.school.service;

import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Article;

import java.util.List;

public interface ArticleService {

    //获取所有物品
    PageInfo<Article> getAllArticles(int pageNum,int pageSize);

    //存储物品信息
    void addArticle(String article_name, String user_id, String article_picture1, String article_time, String article_condition, String article_address, String submit_time, String article_detail);

    //根据ID返回物品
    Article getArticleByID(String article_id);

    //根据用户ID获取所有失物招领信息
    List<Article> getAllArticlesByUID(String user_id);

    //改变物品状态
    void updateCondition(String article_condition, String article_id);
}
