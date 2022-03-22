package com.zzti.school.service;

import com.zzti.school.entity.NReview;
import com.zzti.school.entity.News;

import java.util.List;

public interface NewsService {

    //获取所有新闻
    List<News> getAllNews();

    //根据新闻ID查找新闻
    News findNewsByID(String news_id);

    //根据新闻ID查找所有回复
    List<NReview> getALLReviews(String news_id);

    //添加新闻回复
    void addReview(String news_id, String time, String content, String user_id);

}
