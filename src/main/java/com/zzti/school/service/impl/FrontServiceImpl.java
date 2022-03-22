package com.zzti.school.service.impl;

import com.zzti.school.mapper.FrontMapper;
import com.zzti.school.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    private FrontMapper frontMapper;


    @Override
    public List<Map<Object, Object>> selectAll() {
        return frontMapper.getAll();
    }
}
