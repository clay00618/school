package com.zzti.school.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;



public interface   FrontMapper {


    @Select("select user_id,user_name from table_user")
    List<Map<Object, Object>> getAll();

}
