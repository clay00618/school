package com.zzti.school.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Activity;

import java.util.List;

public interface ActivityService {

    //获取所有活动
    PageInfo<Activity> getAllActivities(int pageNum,int pageSize);

    //存储活动
    void addActivity(String user_id, String name, String start_time, String end_time, String address, String require, String time, String explain);

    //根据活动ID获取所有的用户ID
    List<String> getAllUIDByAID(String activity_id);

    //根据活动ID获取活动
    Activity getActivityByID(String activity_id);

    //存储参加的用户信息
    void addRegister(String user_id, String activity_id, String register_time);

    //根据用户ID和活动ID判断用户是否已经参加活动
    String getJoin(String user_id, String activity_id);


}
