package com.tao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/4/1.
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
