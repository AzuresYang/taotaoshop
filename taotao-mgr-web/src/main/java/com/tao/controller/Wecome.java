package com.tao.controller;

import com.tao.mapper.TbUserMapper;
import com.tao.pojo.TbUser;
import com.tao.pojo.TbUserExample;
import com.tao.service.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/3/19.
 */
@Controller
public class Wecome {
    @Autowired
    UserItem userItem;

    @RequestMapping("/sion")
    public String sion()
    {
        System.out.println(userItem.getCount());

        return "show_session";
    }
    @RequestMapping("/jion/{itemId}")
    @ResponseBody
    public TbUser jion(@PathVariable long itemId)
    {
        System.out.println("查询id:"+itemId);
        return userItem.getUserById(itemId);
    }

}
