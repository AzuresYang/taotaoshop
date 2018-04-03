package com.tao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/3/31.
 */
@Controller
public class TestProtal {
   @RequestMapping("/hello")
    public String helloProtal(){
       System.out.println("Hello");
       return "hello";
   }
}
