package com.zzti.school.service;

import com.zzti.school.entity.User;

import java.util.List;

public interface UserService {


    //添加用户
    void insertUser(String user_student_num,String password,Integer user_type,String user_join_time,String user_school,String user_department,String user_class,String user_tel,String user_state);
    //根据学号和密码查找用户
    boolean findUser(String user_student_num,String password);
    //根据用户名查找用户
    boolean findUserByName(String username);
    //根据学号返回用户ID
    String findIDByNum(String user_student_num);
    ////根据ID查找用户名
    String findNameByID(String user_id);

    //根据ID返回用户
    User findUserByID(String user_id);

    //修改个人资料
    void updateUserByID(String user_name, String user_sex, String user_age, String user_student_num, String user_nickname, String user_school, String user_department, String user_class, String user_tel,String user_join_time,String user_state, String user_id);

    //根据学号修改密码
    void updatePasswordByStu(String password, String user_student_num);

    //根据学号查找用户
    User findUserByStu(String user_student_num);

    //根据学号查找学生权限级别，为1时允许登录
    Integer findTypeByStu(String user_student_num);

    //根据ID查找user_type
    Integer findTypeByID(String user_id);

    //根据ID查找user_state
    String findStateByID(String user_id);

    //获取根据用户所在学院和班级对应的state
    String findStateByClass(String department, String stu_class);

    //获取学院列表
    List<String> getAllDepartment();

    //获取班级列表
    List<String> getAllClass();



}
