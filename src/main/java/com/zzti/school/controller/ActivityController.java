package com.zzti.school.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Activity;
import com.zzti.school.entity.User;
import com.zzti.school.service.ActivityService;
import com.zzti.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;


    @GetMapping("/activity")
    public String activity(Model model, HttpServletRequest request, Integer pageNum){
        if (pageNum == null){
            pageNum = 1;
        }
        PageInfo<Activity> pageInfo = activityService.getAllActivities(pageNum, 10);
/*        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("每页记录数:"+pageInfo.getPageSize());
        System.out.println("总记录数:"+pageInfo.getTotal());*/
        model.addAttribute("pageInfo", pageInfo);
        //列表序号
        model.addAttribute("number", 10 * (pageNum-1));
        return "activity";
    }

/*    @GetMapping("/activity_release")
    public String activity_release(Model model){
        return "activity_release";
    }*/
/*    @GetMapping("/save_activity")
    public String save_activity(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        String name = request.getParameter("activity-name");
        String start_time = request.getParameter("activity-start-time");
        String end_time = request.getParameter("activity-end-time");
        String address = request.getParameter("activity-address");
        String number = request.getParameter("activity-require");
        String time = request.getParameter("activity-time");
        String explain = request.getParameter("activity-explain");
        activityService.addActivity(user_id,name,start_time,end_time,address,number,time,explain);
        System.out.println("添加活动成功！");
        List<Activity> activityList = activityService.getAllActivities();
        model.addAttribute("activityList", activityList);
        return "activity";
    }*/

    @GetMapping("/activity_content")
    public String activity_content(HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        Activity activity = activityService.getActivityByID(id);
        model.addAttribute("activity", activity);
        //获取用户ID列表
        List<String> uidList = activityService.getAllUIDByAID(id);
        //用户名列表
        List<User> userList = new ArrayList<>();
        for (int i=0;i<uidList.size();i++){
            userList.add(userService.findUserByID(uidList.get(i)));
        }
        if(userList.size() != 0){
            String attend = "";
            for (int j=0;j<userList.size()-1;j++){
                attend = attend + userList.get(j).getUser_name() + ",";
            }
            attend = attend + userList.get(userList.size() - 1).getUser_name();
            model.addAttribute("attend", attend);
            return "activity_content";
        }else {
            model.addAttribute("attend","暂无学生报名");
            return "activity_content";
        }

    }

    @GetMapping("/activity_join")
    public String activity_join(HttpServletRequest request ,Model model) throws ParseException {
        User user0 = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user0.getUser_student_num());
        User user = userService.findUserByID(user_id);
        String activity_id = request.getParameter("activity_id");
        Activity activity = activityService.getActivityByID(activity_id);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        if (date.before(format.parse(activity.getActivity_register_end_time())) && date.after(format.parse(activity.getActivity_register_start_time())) && user.getUser_tel() != null && user.getUser_class() != null){
            //存储参加的用户信息
            List<String> uidList = activityService.getAllUIDByAID(activity_id);
            for (int j=0;j<uidList.size();j++){
                if (user_id.equals(uidList.get(j))){
                    model.addAttribute("error", "您已参加该活动！");
                    model.addAttribute("activity", activity);
                    List<User> userList = new ArrayList<>();
                    for (int i=0;i<uidList.size();i++){
                        userList.add(userService.findUserByID(uidList.get(i)));
                    }
                    if(userList.size() != 0){
                        String attend = "";
                        for (int m=0;m<userList.size()-1;m++){
                            attend = attend + userList.get(m).getUser_name() + ",";
                        }
                        attend = attend + userList.get(userList.size() - 1).getUser_name();
                        model.addAttribute("attend", attend);
                        return "activity_content";
                    }else {
                        model.addAttribute("attend","暂无学生报名");
                        return "activity_content";
                    }
                }
            }
            activityService.addRegister(user_id,activity_id,format.format(date));
            List<String> uidList1 = activityService.getAllUIDByAID(activity_id);
            model.addAttribute("activity", activity);
            List<User> userList = new ArrayList<>();
            for (int i=0;i<uidList1.size();i++){
                userList.add(userService.findUserByID(uidList1.get(i)));
            }
            if(userList.size() != 0){
                String attend = "";
                for (int j=0;j<userList.size()-1;j++){
                    attend = attend + userList.get(j).getUser_name() + ",";
                }
                attend = attend + userList.get(userList.size() - 1).getUser_name();
                model.addAttribute("attend", attend);
                return "activity_content";
            }else {
                model.addAttribute("attend","暂无学生报名");
                return "activity_content";
            }
        }else {
            model.addAttribute("error", "不符合活动报名时间或个人信息不完善!");
            model.addAttribute("activity", activity);
            List<String> uidList = activityService.getAllUIDByAID(activity_id);
            List<User> userList = new ArrayList<>();
            for (int i=0;i<uidList.size();i++){
                userList.add(userService.findUserByID(uidList.get(i)));
            }
            if(userList.size() != 0){
                String attend = "";
                for (int j=0;j<userList.size()-1;j++){
                    attend = attend + userList.get(j).getUser_name() + ",";
                }
                attend = attend + userList.get(userList.size() - 1).getUser_name();
                model.addAttribute("attend", attend);
                return "activity_content";
            }else {
                model.addAttribute("attend","暂无学生报名");
                return "activity_content";
            }
        }
    }




}
