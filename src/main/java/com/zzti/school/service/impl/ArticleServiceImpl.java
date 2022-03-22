package com.zzti.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Article;
import com.zzti.school.mapper.ArticleMapper;
import com.zzti.school.mapper.UserMapper;
import com.zzti.school.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo<Article> getAllArticles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getAllArticles();
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public void addArticle(String article_name, String user_id, String article_picture1, String article_time, String article_condition, String article_address, String submit_time, String article_detail) {
        articleMapper.addArticle(article_name,user_id,article_picture1,article_time,article_condition,article_address,submit_time,article_detail);
    }

    @Override
    public Article getArticleByID(String article_id) {
        return articleMapper.getArticleByID(article_id);
    }

    @Override
    public List<Article> getAllArticlesByUID(String user_id) {
        return articleMapper.getAllArticlesByUID(user_id);
    }

    @Override
    public void updateCondition(String article_condition, String article_id) {
        articleMapper.updateCondition(article_condition, article_id);
    }
}
