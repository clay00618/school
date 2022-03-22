package com.zzti.school.service.impl;

import com.zzti.school.entity.User;
import com.zzti.school.mapper.UserMapper;
import com.zzti.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;


    @Override
    public void insertUser(String user_student_num, String password, Integer user_type, String user_join_time, String user_school, String user_department, String user_class, String user_tel, String user_state) {
        userMapper.addUser(user_student_num,password,user_type,user_join_time,user_school,user_department,user_class,user_state,user_tel);
    }

    @Override
    public boolean findUser(String user_student_num, String password) {
        return userMapper.findUser(user_student_num,password) != null;
    }



    @Override
    public boolean findUserByName(String username) {
        return userMapper.findUserByName(username) != null;
    }

    @Override
    public String findIDByNum(String user_student_num) {
        return userMapper.findIDByNum(user_student_num);
    }

    @Override
    public String findNameByID(String user_id) {
        return userMapper.findNameByID(user_id);
    }

    @Override
    public User findUserByID(String user_id) {
        return userMapper.findUserByID(user_id);
    }

    @Override
    public void updateUserByID(String user_name, String user_sex, String user_age, String user_student_num, String user_nickname, String user_school, String user_department, String user_class, String user_tel, String user_join_time, String user_state, String user_id) {
        userMapper.updateUserByID(user_name, user_sex, user_age, user_student_num, user_nickname, user_school, user_department, user_class, user_tel, user_join_time, user_state, user_id);
    }


    @Override
    public void updatePasswordByStu(String password, String user_student_num) {
        userMapper.updatePasswordByStu(password,user_student_num);
    }

    @Override
    public User findUserByStu(String user_student_num) {
        return userMapper.findUserByStu(user_student_num);
    }

    @Override
    public Integer findTypeByStu(String user_student_num) {
        return userMapper.findTypeByStu(user_student_num);
    }

    @Override
    public Integer findTypeByID(String user_id) {
        return userMapper.findTypeByID(user_id);
    }

    @Override
    public String findStateByID(String user_id) {
        return userMapper.findStateByID(user_id);
    }

    @Override
    public String findStateByClass(String department, String stu_class) {
        return userMapper.findStateByClass(department,stu_class);
    }

    @Override
    public List<String> getAllDepartment() {
        return userMapper.getAllDepartment();
    }

    @Override
    public List<String> getAllClass() {
        return userMapper.getAllClass();
    }


}
