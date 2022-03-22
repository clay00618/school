package com.zzti.school.controller;


import com.zzti.school.entity.News;
import com.zzti.school.entity.User;
import com.zzti.school.service.FrontService;
import com.zzti.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FrontService frontService;

    @Autowired
    private NewsService newsService;

    @GetMapping("/index")
    public String index(Model model){
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "index";
    }

    @GetMapping({"/login","/"})
    public String login(){
        return "login";
    }





/*    @RequestMapping("/getAll.do")
    public @ResponseBody List<Map<Object, Object>> getAll(){
        return frontService.selectAll();
    }*/
}
