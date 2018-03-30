package com.tao.service;

import com.tao.mapper.TbUserMapper;
import com.tao.pojo.TbUser;
import com.tao.service.base.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/27.
 */
public class UserServiceImplTest {
    @Test
    public void createUser() throws Exception {
        TbUser user = new TbUser();
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");

        user.setId((long)1);
        user.setEmail("Hello@qq.com");
        user.setPassword("123");
        user.setPhone("151144444");
        user.setUsername("AAAzhi");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        UserService service = (UserService)appliationContext.getBean("userServiceImpl");
        int result = service.createUser(user);
        System.out.println("create user"+result);
    }

}