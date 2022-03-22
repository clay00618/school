package com.zzti.school.controller;


import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.CReview;
import com.zzti.school.entity.Confession;
import com.zzti.school.entity.User;
import com.zzti.school.service.CReviewService;
import com.zzti.school.service.ConfessionService;
import com.zzti.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfessionController {



    @Autowired
    private UserService userService;
    @Autowired
    private ConfessionService confessionService;


    @Autowired
    private CReviewService cReviewService;
    //表白帖子列表
    @GetMapping("/confession")
    public String confession(Model model,HttpServletRequest request,Integer pageNum){
        if (pageNum == null){
            pageNum =1;
        }
        PageInfo<Confession> pageInfo = confessionService.getAllConfessions(pageNum, 10);
        model.addAttribute("pageInfo", pageInfo);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<pageInfo.getList().size();i++){
            nameList.add(userService.findNameByID(pageInfo.getList().get(i).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "wall";
    }
    //发布表白帖子
    @GetMapping("/wall_release")
    public String wall_release(Model model){

        return "wall_release";
    }
    //查看帖子内容
    @GetMapping("/wall_content")
    public String wall_content(String username,String content,String confession_id,String user_id,String confession_time,HttpServletRequest request,Model model){
        List<CReview> cReviews = cReviewService.getAllCReview(confession_id);
        Confession confession = new Confession(confession_id,confession_time,user_id,content);
        model.addAttribute("username", username);
        model.addAttribute("cReviews", cReviews);
        model.addAttribute("confession", confession);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<cReviews.size();i++){
            nameList.add(userService.findNameByID(cReviews.get(i).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "wall_content";
    }


    //点击发表表白帖时接受参数,当发布匿名帖子时，存储anonymous为2
    @RequestMapping("/wall_submit")
    @ResponseBody
    public Map<String,Object> wall_submit(String text, String time, String anonymous, Model model, HttpServletRequest request){
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        //添加表白帖
        //依次添加帖子ID，发布时间，用户ID，帖子内容
        if (anonymous.equals("yes")){
            User user0 = userService.findUserByID("0");
            confessionService.addConfession(time,"0",text);
        }else {
            confessionService.addConfession(time,user_id,text);
        }
        return resultMap;
    }


    //当点击提交回复时执行此方法，接受参数：回复内容，提交回复时间
    @RequestMapping("/wall_review")
    @ResponseBody
    public Map<String,Object> wall_review(String content,String time,String confession_id,Model model,HttpServletRequest request){
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        cReviewService.addCReview(confession_id,user_id,time,content);
        return resultMap;
    }


    ////ajax返回参数后，跳转到controller层
    @GetMapping("/ajax_wall_review")
    public String ajax_wall_review(HttpServletRequest request,Model model){
        String confession_id = request.getParameter("confession_id");
        List<CReview> cReviews = cReviewService.getAllCReview(confession_id);
        model.addAttribute("cReviews", cReviews);
        Confession confession = confessionService.getConfessionByID(confession_id);
        model.addAttribute("confession", confession);
        String name = userService.findNameByID(confession.getUser_id());
        model.addAttribute("username", name);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<cReviews.size();i++){
            nameList.add(userService.findNameByID(cReviews.get(i).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "wall_content";
    }

}
