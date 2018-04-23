package com.tao.protal.controller;

import com.tao.protal.service.base.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 28029 on 2018/4/1.
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;


    @RequestMapping("/index")
    public String showIndex(Model model){
        String adJson = contentService.getContentList();
        System.out.println("adJson:"+adJson);

        model.addAttribute("ad1",adJson);
        return "index";
    }

    @RequestMapping("/")
    public String showIndexPage(Model model){
        String adJson = contentService.getContentList();
        System.out.println("adJson:"+adJson);

        model.addAttribute("ad1",adJson);
        return "index";
    }

    @RequestMapping("/test")
    public String showTest(Model model){
        String adJson = contentService.getContentList();
        System.out.println("adJson:"+adJson);

        model.addAttribute("ad1",adJson);
        return "test";
    }

    @RequestMapping("/test1")
    public String showTest1(HttpServletResponse response, HttpServletRequest request){
        String adJson = contentService.getContentList();
        System.out.println("adJson:"+adJson);

        request.setAttribute("ad1",adJson);
        return "test";
    }
}
