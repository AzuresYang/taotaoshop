package com.tao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/3/22.
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page)
    {
        return page;
    }
}
