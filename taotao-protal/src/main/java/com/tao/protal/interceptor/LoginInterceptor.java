package com.tao.protal.interceptor;

import com.tao.pojo.TbUser;
import com.tao.protal.service.UserServiceImpl;

import com.tao.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 28029 on 2018/4/14.
 */
public class LoginInterceptor implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private UserServiceImpl userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //在Handler执行之前处理
        //判断用户是否登录
        //从cookie中取token链接
        String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
        TbUser tbUser = userService.getUserByToken(token);
        if(tbUser == null)
        {
            logger.info("cannt find token user");
           response.sendRedirect(userService.SSO_BASE_URL+userService.SSO_PAGE_LOGIN_URL
                   + "?redirect="+request.getRequestURL());
           return false;
        }

        logger.info("token user:"+tbUser.getUsername());
        //读取用户信息，放行
        request.setAttribute("user",tbUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // handler执行之后，返回ModelAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 返回ModelAndView之后。
        //响应用户之后。

    }
}
