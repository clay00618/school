package com.zzti.school.service;

import com.zzti.school.entity.CReview;

import java.util.List;

public interface CReviewService {

    //添加回复
    void addCReview(String confession_id, String user_id, String confession_review_time, String confession_review_content);

    //根据表白帖子ID获取所有表白回复帖
    List<CReview>
    getAllCReview(String confession_id);
}
