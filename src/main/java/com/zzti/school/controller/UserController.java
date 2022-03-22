package com.zzti.school.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zzti.school.entity.*;
import com.zzti.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ConfessionService confessionService;

    @Autowired
    private CReviewService cReviewService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/user")
    public String addUser(String stu_num, String login_password, Model model,HttpServletRequest request){
        System.out.println("学号: "+stu_num+"密码: "+login_password);
        HttpSession session = request.getSession();
        if(userService.findUser(stu_num,login_password)&&userService.findTypeByStu(stu_num)==1){
            User user = userService.findUserByStu(stu_num);
            session.setAttribute("user",user);
        }else {
            if (userService.findUserByStu(stu_num) != null){
                model.addAttribute("pw","密码错误！");
                return "login";
            }else {
                char[] chars = stu_num.toCharArray();
                for (char c : chars){
                    if (!(c >= '0' && c <='9')){
                        model.addAttribute("stu", "请输入有效的学号！");
                        return "login";
                    }
                }
                model.addAttribute("exist", "账号不存在!");
                return "login";
            }
        }
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "index";
    }

    @GetMapping("/news_content")
    public String news_content(Model model,HttpServletRequest request,String id){
        News news = newsService.findNewsByID(id);
        List<NReview> nReviewList = newsService.getALLReviews(id);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<nReviewList.size();i++){
            nameList.add(userService.findNameByID(nReviewList.get(i).getUser_id()));
        }
        model.addAttribute("news", news);
        model.addAttribute("nReviewList", nReviewList);
        model.addAttribute("nameList", nameList);
        return "news_content";
    }

    @RequestMapping("/news_review")
    @ResponseBody
    public Map<String, Object> news_review(String content, String time, String news_id,HttpServletRequest request) {
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("result", "SUCCESS");
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        newsService.addReview(news_id,time,content,user_id);
        return resultMap;
    }

    @GetMapping("news_ajax_review")
    public String news_ajax_review(HttpServletRequest request,Model model){
        String news_id = request.getParameter("news_id");
        News news = newsService.findNewsByID(news_id);
        List<NReview> nReviewList = newsService.getALLReviews(news_id);
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<nReviewList.size();i++){
            nameList.add(userService.findNameByID(nReviewList.get(i).getUser_id()));
        }
        model.addAttribute("news", news);
        model.addAttribute("nReviewList", nReviewList);
        model.addAttribute("nameList", nameList);
        return "news_content";

    }

    @PostMapping("/modify")
    public String modify(String stu_num,String new_password,String confirm_password,Model model,HttpServletRequest request){
        System.out.println("修改用户的学号:"+stu_num+"新密码："+new_password+"确认新密码:"+confirm_password);
        if (!new_password.equals(confirm_password)){
            model.addAttribute("pw", "密码不一致！");
            return "modify_password";
        }else {
            userService.updatePasswordByStu(new_password,stu_num);
            System.out.println("修改密码成功！");
        }
        return "login";
    }

    @GetMapping("/modify_password")
    public String modify_password(){
        return "modify_password";
    }



    //搜索框
    @GetMapping("/search_everywhere")
    public String search_everywhere(HttpServletRequest request, Model model){
        List<String> nameList = new ArrayList<>();
        String name = request.getParameter("title");
        PageHelper.startPage(1,10);
        List<Node> nodeList = nodeService.findNodesByContent(name);
        System.out.println(nodeList);
        for ( int i =0;i < nodeList.size();i++){
            nameList.add(userService.findNameByID(nodeList.get(i).getUser_id()));
        }
        PageInfo<Node> pageInfo = new PageInfo<>(nodeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("nameList", nameList);
        return "node";
    }

    //获取当前用户的帖子回复列表和表白帖子回复列表
    @GetMapping("/message")
    public String message(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        List<Node> nodeList = nodeService.getAllNodesByUID(user_id);
        Map<String, List<Review>> nodeMap  = new HashMap<>();
        Map<String, List<String>> nameMap = new HashMap<>();
        System.out.println("nodeList:" + nodeList);
        for (int i=0;i<nodeList.size();i++){
            List<String> nameList = new ArrayList<>();
            List<Review>  reviewList  =reviewService.getAllReviews(nodeList.get(i).getTopic_id());
            nodeMap.put(nodeList.get(i).getTopic_name(), reviewList);
            for (int j=0;j<reviewList.size();j++){
                nameList.add(userService.findNameByID(reviewList.get(j).getUser_id()));
            }
            nameMap.put(nodeList.get(i).getTopic_name(), nameList);
        }
        List<Confession> confessionList = confessionService.getAllConfessionsByUID(user_id);
        Map<String, List<CReview>> cnodeMap = new HashMap<>();
        Map<String, List<String>> cnameMap = new HashMap<>();
        for (int i=0;i<confessionList.size();i++){
            List<String> nameList = new ArrayList<>();
            List<CReview> cReviewList = cReviewService.getAllCReview(confessionList.get(i).getConfession_id());
            cnodeMap.put(confessionList.get(i).getConfession_time(), cReviewList);
            for (int j=0;j<cReviewList.size();j++){
                nameList.add(userService.findNameByID(cReviewList.get(j).getUser_id()));
            }
            cnameMap.put(confessionList.get(i).getConfession_time(), nameList);
        }
        model.addAttribute("cnodeMap", cnodeMap);
        model.addAttribute("cnameMap", cnameMap);
        model.addAttribute("nodeMap", nodeMap);
        model.addAttribute("nameMap", nameMap);
        return "message";
    }

    //获取个人资料
    @GetMapping("/info")
    public String info(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        User user1 = userService.findUserByID(user_id);
        model.addAttribute("user", user1);
        return "info";
    }

    //编辑个人资料
    @GetMapping("/edit_info")
    public String edit(){
        return "edit_info";
    }

    //存储个人资料
    @RequestMapping(value = "/save_info",method = RequestMethod.POST)
    public String save(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String student_num = request.getParameter("student_num");
        String nickname = request.getParameter("nickname");
        String school = request.getParameter("school");
        String department = request.getParameter("department");
        String student_class = request.getParameter("student_class");
        String tel = request.getParameter("tel");
        String user_state = userService.findStateByClass(department, student_class);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        userService.updateUserByID(username,sex,age,student_num,nickname,school,department,student_class,tel,format.format(date),user_state,user_id);
        System.out.println("保存个人资料成功！");
        User user2 = userService.findUserByID(user_id);
        model.addAttribute("user", user2);
        return "info";
    }
    //获取用户发布的帖子
    @GetMapping("/my_submit")
    public String my_submit(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        List<Node> nodeList = nodeService.getAllNodesByUID(user_id);
        model.addAttribute("nodeList", nodeList);
        List<Confession> confessionList = confessionService.getAllConfessionsByUID(user_id);
        model.addAttribute("confessionList", confessionList);
        return "my_submit";
    }
    //获取用户发布的实物招领
    @GetMapping("/my_submit_article")
    public String my_submit_article(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        List<Article>  articleList =articleService.getAllArticlesByUID(user_id);
        List<String> stateList = new ArrayList<>();
        for (int i=0;i<articleList.size();i++){
            if (articleList.get(i).getArticle_condition().equals("1")){
                stateList.add("未认领");
            }
            if (articleList.get(i).getArticle_condition().equals("2")){
                stateList.add("认领中");
            }
            if (articleList.get(i).getArticle_condition().equals("3")){
                stateList.add("已认领");
            }
        }
        model.addAttribute("stateList", stateList);
        model.addAttribute("articleList", articleList);
        return "my_submit_article";
    }

    //获取用户发布的举报和建议信息
    @GetMapping("/my_submit_advice")
    public String my_submit_advice(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        List<Report> reportList = reportService.getAllReportsByUID(user_id);
        model.addAttribute("reportList", reportList);
        return "my_submit_advice";
    }

    //注册
    @GetMapping("/stu_register")
    public String stu_register(Model model){
        List<String> departments0 = userService.getAllDepartment();
        List<String> classes0 = userService.getAllClass();
        List<String> departments = new ArrayList<>();
        List<String> classes = new ArrayList<>();
        departments.add(departments0.get(0));
        classes.add(classes0.get(0));
        int k=0;
        for (int i=0;i<departments0.size();i++){
            if (!departments.get(k).equals(departments0.get(i))){
                departments.add(departments0.get(i));
                k++;
            }
        }
        int m=0;
        for (int i=0;i<classes0.size();i++){
            if (!classes.get(m).equals(classes0.get(i))){
                classes.add(classes0.get(i));
                m++;
            }
        }
        model.addAttribute("departments", departments);
        model.addAttribute("classes", classes);
        return "stu_register";
    }

    //注册之后添加用户信息
    @PostMapping("/register")
    public String register(Model model,String stu_num,String login_password,String department_select,String class_select,String stu_tel){
        System.out.println("stu_num:"+stu_num);
        System.out.println("password:"+login_password);
        System.out.println("department:"+department_select);
        System.out.println("class:"+class_select);
        System.out.println("stu_tel:"+stu_tel);
        //获取注册时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取用户权限
        String state = userService.findStateByClass(department_select, class_select);
        //学校为中原工学院(写死)
        userService.insertUser(stu_num,login_password,1,format.format(date),"中原工学院",department_select,class_select,stu_tel,state);
        return "login";
    }

    //退出，返回登录界面
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUser_name() != null){
            request.getSession().removeAttribute("user");
        }
        return "login";
    }
}
