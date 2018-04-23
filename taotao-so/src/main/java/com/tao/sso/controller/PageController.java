package com.tao.sso.controller;

import com.tao.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 28029 on 2018/4/10.
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage(String redirect, Model model,HttpServletResponse response,HttpServletRequest request){
        System.out.println("redirect is :"+redirect);
        model.addAttribute("redirect",redirect);

            showAndSetCookie(request,response);
        return "login";
    }

    public void showAndSetCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
        } else {
            System.out.println("cookie:");
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }
        Cookie a = new Cookie("testCookie","hello");
        CookieUtils.setCookie(request,response,"utilTest","emmm");
        response.addCookie(a);
    }
}
