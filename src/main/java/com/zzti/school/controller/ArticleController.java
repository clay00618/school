package com.zzti.school.controller;


import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Article;
import com.zzti.school.entity.User;
import com.zzti.school.service.ArticleService;
import com.zzti.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    //获取招领信息
    @GetMapping("/article")
    public String article(Model model,Integer pageNum){
        if (pageNum == null){
            pageNum = 1;
        }
        PageInfo<Article> pageInfo = articleService.getAllArticles(pageNum, 10);
        List<String> stateList = new ArrayList<>();
        for (int i=0;i<pageInfo.getList().size();i++){
            if (pageInfo.getList().get(i).getArticle_condition().equals("1")){
                stateList.add("未认领");
            }
            if (pageInfo.getList().get(i).getArticle_condition().equals("2")){
                stateList.add("认领中");
            }
            if (pageInfo.getList().get(i).getArticle_condition().equals("3")){
                stateList.add("已认领");
            }
        }
        model.addAttribute("stateList", stateList);
        model.addAttribute("pageInfo", pageInfo);
        return "article";
    }
    //跳转至招领信息发布页面
    @GetMapping("/article_submit")
    public String article_submit(Model model){
        return "article_submit";
    }
    //添加招领信息
    @PostMapping("/add_article")
    public String add_article(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        String name = request.getParameter("article_name");
        //拾物时间
        String time = request.getParameter("article_time");
        String address = request.getParameter("article_address");
        String detail = request.getParameter("article_detail");
        String imageData = request.getParameter("imagedata");
/*        System.out.println(imageData);
        System.out.println(name);
        System.out.println(time);
        System.out.println(address);
        System.out.println(detail);*/
        String []pic = imageData.split(",");
        //图片base64码
        String base = pic[1];
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        articleService.addArticle(name,user_id,base,time,"1",address,format.format(date),detail);
        PageInfo<Article> pageInfo = articleService.getAllArticles(1, 10);
        List<String> stateList = new ArrayList<>();
        for (int i=0;i<pageInfo.getList().size();i++){
            if (pageInfo.getList().get(i).getArticle_condition().equals("1")){
                stateList.add("未认领");
            }
            if (pageInfo.getList().get(i).getArticle_condition().equals("2")){
                stateList.add("认领中");
            }
            if (pageInfo.getList().get(i).getArticle_condition().equals("3")){
                stateList.add("已认领");
            }
        }
        model.addAttribute("stateList", stateList);
        model.addAttribute("pageInfo", pageInfo);
        return "article";
    }
    //获取物品详情
    @GetMapping("/article_content")
    public String article_content(Model model,String article_id){
        Article article = articleService.getArticleByID(article_id);
        User user = userService.findUserByID(article.getUser_id());
        String state = "";
        if (article.getArticle_condition().equals("1")){
            state = "未认领";
        }
        if (article.getArticle_condition().equals("2")){
            state = "认领中";
        }
        if (article.getArticle_condition().equals("3")){
            state = "已认领";
        }
        model.addAttribute("username", user.getUser_name());
        model.addAttribute("tel", user.getUser_tel());
        model.addAttribute("state", state);
        model.addAttribute("article", article);
        return "article_content";
    }
    //修改物品状态
    @GetMapping("/condition")
    public String condition(Model model,HttpServletRequest request,String state,String id){
        if (state.equals("未认领")){
            articleService.updateCondition("1",id);
        }
        if (state.equals("认领中")){
            articleService.updateCondition("2",id);
        }
        if (state.equals("已认领")){
            articleService.updateCondition("3",id);
        }
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

}
