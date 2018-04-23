package com.tao.sso.controller;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbUser;
import com.tao.sso.service.base.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 28029 on 2018/4/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback){
        System.out.println("check:"+param+"  type:"+type);
        ResponseResult result = null;

        if(StringUtils.isBlank(param))
        {
            result = ResponseResult.build(400,"检验内容不能为空",null);
        }
        if(type == null)
        {
            result = ResponseResult.build(400,"检验内容类型不能为空",null);
        }
        if (type != 1 && type != 2 && type != 3 ) {
            result = ResponseResult.build(400, "校验内容类型错误",null);
        }

        //校验出错,调用jsonP方法
        if(null != result)
        {
            if(null != callback)
            {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else
                return result;
        }

        //调用服务
        try{
            result = userService.checkData(param,type);
        }catch (Exception e)
        {
            result = ResponseResult.build(500, e.getMessage(), null);
        }

        if(null != callback)
        {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

        return result;
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult createUser(TbUser user)
    {
        try{
            ResponseResult result = userService.createUser(user);
            return  result;
        }catch(Exception e)
        {
            return ResponseResult.build(500,e.getMessage(),null);
        }
    }

    //用户登录
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public ResponseResult userLogin(String username, String password,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {

            ResponseResult result = userService.userLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.build(500, e.getMessage(),null);
        }
    }

    public void showAndSetCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
        } else {
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }
    }
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        ResponseResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseResult.build(500, e.getMessage(),null);
        }

        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

    }
}
