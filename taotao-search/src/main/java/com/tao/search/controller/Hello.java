package com.tao.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/4/7.
 */
@Controller
public class Hello {
   @RequestMapping("/hello")
    public String hello(){
       System.out.println("search hello");
       return "hello";
   }
}
