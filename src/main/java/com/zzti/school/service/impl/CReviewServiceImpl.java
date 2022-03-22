package com.zzti.school.service.impl;

import com.zzti.school.entity.CReview;
import com.zzti.school.mapper.CReviewMapper;
import com.zzti.school.service.CReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CReviewServiceImpl implements CReviewService {

    @Autowired
    private CReviewMapper cReviewMapper;


    @Override
    public void addCReview(String confession_id, String user_id, String confession_review_time, String confession_review_content) {
        cReviewMapper.addCReview(confession_id,user_id,confession_review_time,confession_review_content);
    }

    @Override
    public List<CReview> getAllCReview(String confession_id) {
        return cReviewMapper.getAllCReview(confession_id);
    }
}
