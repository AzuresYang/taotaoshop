package com.tao.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




/**
 * Created by 28029 on 2018/3/31.
 */
@Controller
public class Hello {
    private static Logger logger = LoggerFactory.getLogger(Hello.class);
    @RequestMapping("/log")
    public String hello(){
        System.out.println("helo");
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        return "hello";
    }
}
