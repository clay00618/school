package com.zzti.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Confession;
import com.zzti.school.entity.Node;
import com.zzti.school.mapper.ConfessionMapper;
import com.zzti.school.service.ConfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConfessionServiceImpl implements ConfessionService {

    @Autowired
    private ConfessionMapper confessionMapper;


    @Override
    public void addConfession(String confession_time, String user_id, String confession_content) {
        confessionMapper.addConfession(confession_time,user_id,confession_content);
    }

    @Override
    public PageInfo<Confession> getAllConfessions(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Confession> confessionList = confessionMapper.getAllConfessions();
        PageInfo<Confession> pageInfo = new PageInfo<>(confessionList);
        return pageInfo;
    }


    @Override
    public Confession getConfessionByID(String confession_id) {
        return confessionMapper.getConfessionByID(confession_id);
    }

    @Override
    public List<Confession> getAllConfessionsByUID(String user_id) {
        return confessionMapper.getAllConfessionsByUID(user_id);
    }
}
