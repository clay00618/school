package com.zzti.school.service;

import com.zzti.school.entity.Review;

import java.util.List;

public interface ReviewService {

    //根据帖子ID返回所有评论
    List<Review> getAllReviews(String node_id);

    //存储新提交的回复
    void addReview(String topic_id, String topic_review_time, String topic_review_content, String user_id);

}
