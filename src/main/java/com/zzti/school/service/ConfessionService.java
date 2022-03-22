package com.zzti.school.service;

import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Confession;

import java.util.List;

public interface ConfessionService {

    //添加表白帖
    void addConfession(String confession_time, String user_id, String confession_content);

    //获取所有表白帖
    PageInfo<Confession> getAllConfessions(int pageNum,int pageSize);

    //根据帖子ID查找帖子
    Confession getConfessionByID(String confession_id);

    ////根据用户ID返回所有表白帖
    List<Confession> getAllConfessionsByUID(String user_id);
}
