package com.zzti.school.mapper;

import com.zzti.school.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {


    //根据学号和密码查找用户
    @Select("select * from user where user_student_num = #{user_student_num} and  password = #{password}")
    User findUser(@Param("user_student_num") String user_student_num, @Param("password") String password);

    //根据学号查找用户
    @Select("select * from user where user_student_num = #{user_student_num}")
    User findUserByStu(@Param("user_student_num") String user_student_num);

    //根据用户名查找用户
    @Select("select user_name from user where user_name = #{username}")
    String findUserByName(@Param("username") String username);


    //根据学号，密码添加用户
    @Insert("insert into user (user_id, user_student_num, password, user_school, user_department, user_class, user_tel, user_type, user_join_time, user_state) values ((select max_id from (" +
            "select max(cast(user_id as signed))+1 as max_id from user) as m),#{user_student_num},#{password},#{user_school},#{user_department},#{user_class},#{user_tel} ,#{user_type},#{user_join_time},#{user_state})")
    void addUser(@Param("user_student_num") String user_student_num, @Param("password") String password, @Param("user_type")Integer user_type,@Param("user_join_time")String user_join_time,@Param("user_school")String user_school,@Param("user_department")String user_department,@Param("user_class")String user_class,@Param("user_state")String user_state,@Param("user_tel")String user_tel);

    //根据学号返回用户ID
    @Select("select user_id from user where user_student_num = #{user_student_num}")
    String findIDByNum(@Param("user_student_num") String user_student_num);


    //根据ID查找用户名
    @Select("select user_name from user where user_id = #{user_id}")
    String findNameByID(@Param("user_id") String user_id);

    //根据ID返回用户
    @Select("select * from user where user_id = #{user_id}")
    User findUserByID(@Param("user_id") String user_id);

    //修改个人资料
    @Update("update user set user_name = #{user_name},user_sex = #{user_sex},user_age = #{user_age},user_student_num = #{user_student_num},user_nickname = #{user_nickname},user_school = #{user_school},user_department = #{user_department},user_class = #{user_class},user_tel = #{user_tel},user_join_time = #{user_join_time},user_state = #{user_state} where user_id = #{user_id}")
    void updateUserByID(@Param("user_name") String user_name, @Param("user_sex") String user_sex, @Param("user_age") String user_age, @Param("user_student_num") String user_student_num, @Param("user_nickname") String user_nickname, @Param("user_school") String user_school, @Param("user_department") String user_department, @Param("user_class") String user_class, @Param("user_tel") String user_tel, @Param("user_join_time")String user_join_time,@Param("user_state")String user_state,@Param("user_id") String user_id);

    //根据学号修改密码
    @Update("update user set password = #{password} where user_student_num = #{user_student_num}")
    void updatePasswordByStu(@Param("password")String password,@Param("user_student_num")String user_student_num);

    //根据学号查找学生权限级别，为1时允许登录
    @Select("select user_type from user where user_student_num = #{user_student_num}")
    Integer findTypeByStu(@Param("user_student_num") String user_student_num);

    //根据ID查找user_type
    @Select("select user_type from user where user_id = #{user_id}")
    Integer findTypeByID(@Param("user_id") String user_id);

    //根据ID查找user_state
    @Select("select user_state from user where user_id = #{user_id}")
    String findStateByID(@Param("user_id") String user_id);

    //获取根据用户所在学院和班级对应的state
    @Select("select user_state from class_number where department = #{department} and class = #{stu_class}")
    String findStateByClass(@Param("department") String department, @Param("stu_class")String stu_class);


    //获取学院列表
    @Select("select department from class_number")
    List<String> getAllDepartment();

    //获取班级列表
    @Select("select class from class_number")
    List<String> getAllClass();


}
