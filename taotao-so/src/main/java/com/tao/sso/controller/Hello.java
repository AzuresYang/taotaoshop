package com.tao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/4/9.
 */
@Controller
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello sso");
        return "hello";
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("index h");
        return "index";
    }
}
