package com.tao.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/3/31.
 */
@Controller
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("helo");
        return "hello";
    }
}
