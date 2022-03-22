package com.zzti.school.controller;

import com.zzti.school.entity.User;
import com.zzti.school.service.ReportService;
import com.zzti.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class AdviceController {


    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;


    @GetMapping("/advice")
    public String advice(){
        return "advice";
    }



    //kind为1时存储建议，为0时存储举报
    @RequestMapping("/advice")
    @ResponseBody
    public Map<String,Object> advice(String content, String time, String kind, HttpServletRequest request){
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        if (kind.equals("建议")){
            reportService.addReport(user_id,"1",time,content);
        }else {
            reportService.addReport(user_id,"0",time,content);
        }
        System.out.println("添加举报和建议信息成功！");
        return  resultMap;
    }


}
