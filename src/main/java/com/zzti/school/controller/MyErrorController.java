package com.zzti.school.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;


@Component
public class MyErrorController implements ErrorViewResolver {


    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView();
        switch (status){
            case NOT_FOUND:{
                mv.setViewName("redirect:/static/404.html");
                break;
            }
            case BAD_REQUEST:{
                mv.setViewName("redirect:/static/400.html");
                break;
            }
            case INTERNAL_SERVER_ERROR:{
                mv.setViewName("redirect:/static/500.html");
                break;
            }
            default:{
                mv.setViewName("redirect:/static/error.html");
            }
        }
        return mv;
    }
}
