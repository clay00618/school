package com.zzti.school.controller;

import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Node;
import com.zzti.school.entity.Review;
import com.zzti.school.entity.User;
import com.zzti.school.service.NodeService;
import com.zzti.school.service.ReviewService;
import com.zzti.school.service.UserService;
import com.zzti.school.service.impl.NodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class NodeController {


    @Autowired
    private NodeService nodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;
    //点击发表时接受参数
    @RequestMapping("/submit")
    @ResponseBody
    public Map<String,Object> submit(String text,String title,String time, HttpServletRequest request) throws ParseException {
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        System.out.println(text);
        System.out.println(title);
        System.out.println(time);
/*        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        System.out.println("格式化后的日期："+date);*/
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        nodeService.addNode(title,text,time,user_id);
        return resultMap;
    }
    //获取所有帖子，得到帖子列表
    @GetMapping("/node")
    public String node(HttpServletRequest request, Model model,Integer pageNum){
        if (pageNum == null){
            pageNum =1;
        }
        //String username = request.getParameter("username");
/*        if (username != null){
            System.out.println("username不为空");
        }else {
            System.out.println("username为空");
        }*/
        //model.addAttribute("username", username);
        List<String> nameList = new ArrayList<>();
        PageInfo<Node> pageInfo = nodeService.getAllNodes(pageNum, 10);
        for ( int i =0;i < pageInfo.getList().size();i++){
            nameList.add(userService.findNameByID(pageInfo.getList().get(i).getUser_id()));
        }
        System.out.println("发帖用户列表："+nameList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("nameList", nameList);
        return "node";
    }
    //发布帖子
    @GetMapping("/release")
    public String release(){
        return "release";
    }

    //查看帖子详情
    @GetMapping("/node_content")
    public String node_content(){
        return "node_content";
    }

    //当点击帖子标题时执行此方法，接受参数：帖子名称，发表人，帖子内容
    @GetMapping("/content")
    public String content(String title, String user_id, String content,String node_id,String time,Model model){
        List<Review> reviews = reviewService.getAllReviews(node_id);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<reviews.size();i++){
            nameList.add(userService.findNameByID(reviews.get(i).getUser_id()));
        }
        System.out.println("回复用户列表："+nameList);
        model.addAttribute("reviews", reviews);
        Node node = new Node();
        node.setTopic_name(title);
        node.setTopic_content(content);
        node.setTopic_id(node_id);
        node.setTopic_time(time);
        node.setUser_id(user_id);
        model.addAttribute("node",node);
        String name = userService.findNameByID(user_id);
        model.addAttribute("username", name);
        model.addAttribute("nameList", nameList);
        return "node_content";
    }

    //当点击提交回复时执行此方法，接受参数：回复内容，提交回复时间,id为当前回复的帖子ID
    @RequestMapping("/review")
    @ResponseBody
    public Map<String,Object> review(String content,String time,String node_id,HttpServletRequest request){
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        reviewService.addReview(node_id,time,content,user_id);
        return  resultMap;
    }


    //ajax返回参数后，跳转到controller层
    @GetMapping("/ajax_review")
    public String ajax_review(HttpServletRequest request,Model model){
        String node_id = request.getParameter("node_id");
        List<Review>  reviews =  reviewService.getAllReviews(node_id);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<reviews.size();i++){
            nameList.add(userService.findNameByID(reviews.get(i).getUser_id()));
        }
        System.out.println("返回回复用户列表："+nameList);
        model.addAttribute("nameList", nameList);
        model.addAttribute("reviews", reviews);
        Node node = nodeService.findNodeByID(node_id);
        String name = userService.findNameByID(node.getUser_id());
        model.addAttribute("username", name);
        model.addAttribute("node", node);
        return "node_content";
    }



}
