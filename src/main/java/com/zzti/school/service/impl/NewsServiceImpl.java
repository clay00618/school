package com.zzti.school.service.impl;

import com.zzti.school.entity.NReview;
import com.zzti.school.entity.News;
import com.zzti.school.mapper.NewsMapper;
import com.zzti.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public List<News> getAllNews() {
        return newsMapper.getAllNews();
    }

    @Override
    public News findNewsByID(String news_id) {
        return newsMapper.findNewsByID(news_id);
    }

    @Override
    public List<NReview> getALLReviews(String news_id) {
        return newsMapper.getALLReviews(news_id);
    }

    @Override
    public void addReview(String news_id, String time, String content, String user_id) {
        newsMapper.addReview(news_id,time,content,user_id);
    }
}
