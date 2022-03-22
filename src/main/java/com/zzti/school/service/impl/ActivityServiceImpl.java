package com.zzti.school.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Activity;
import com.zzti.school.entity.User;
import com.zzti.school.mapper.ActivityMapper;
import com.zzti.school.mapper.UserMapper;
import com.zzti.school.service.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo<Activity> getAllActivities(int pageNum,int pageSize) {
        //startPage对最近的一次查询有效
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> activityList0 = activityMapper.getAllActivities();
        PageInfo<Activity> pageInfo = new PageInfo<>(activityList0);
        //需要转换的对象
        PageInfo<Activity> target = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,target);
        List<Activity> activityList = new ArrayList<>(pageInfo.getList());
        List<Activity> activityList1 = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
        for (int i=0;i<pageInfo.getList().size();i++){
            //创建者ID列表
            typeList.add(userMapper.findTypeByID(activityList.get(i).getUser_id()));
        }
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userMapper.findIDByNum(user.getUser_student_num());
        for (int j=0;j<typeList.size();j++){
            //班级管理员
            if (typeList.get(j)==2){
                //登录用户权限范围
                String user_state = userMapper.findStateByID(user_id);
                String manager_state = userMapper.findStateByID(activityList.get(j).getUser_id());
                if (user_state.equals(manager_state)){
                    activityList1.add(activityList.get(j));
                }
            }
            //院级管理员
            if (typeList.get(j)==3){
                String user_state = userMapper.findStateByID(user_id);
                String manager_state = userMapper.findStateByID(activityList.get(j).getUser_id());
                String sub_user_state = user_state.substring(4, 6);
                String sub_manager_state = manager_state.substring(4, 6);
                if (sub_user_state.equals(sub_manager_state)){
                    activityList1.add(activityList.get(j));
                }
            }
            //校级管理员
            if (typeList.get(j)==5){
                activityList1.add(activityList.get(j));
            }
        }
        target.setList(activityList1);
        return target;
    }

    @Override
    public void addActivity(String user_id, String name, String start_time, String end_time, String address, String require, String time, String explain) {
        activityMapper.addActivity(user_id,name,start_time,end_time,address,require,time,explain);
    }

    @Override
    public List<String> getAllUIDByAID(String activity_id) {
        return activityMapper.getAllUIDByAID(activity_id);
    }

    @Override
    public Activity getActivityByID(String activity_id) {
        return activityMapper.getActivityByID(activity_id);
    }

    @Override
    public void addRegister(String user_id, String activity_id, String register_time) {
        activityMapper.addRegister(user_id,activity_id,register_time);
    }

    @Override
    public String getJoin(String user_id, String activity_id) {
        return activityMapper.getJoin(user_id,activity_id);
    }
}
