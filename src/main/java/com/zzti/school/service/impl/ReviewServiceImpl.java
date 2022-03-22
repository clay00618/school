package com.zzti.school.service.impl;

import com.zzti.school.entity.Review;
import com.zzti.school.mapper.ReviewMapper;
import com.zzti.school.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Review> getAllReviews(String node_id) {
        return reviewMapper.getAllReviews(node_id);
    }

    @Override
    public void addReview(String topic_id, String topic_review_time, String topic_review_content, String user_id) {
        reviewMapper.addReview(topic_id,topic_review_time,topic_review_content,user_id);
    }
}
