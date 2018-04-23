package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItemDesc;
import com.tao.protal.service.base.ItemService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/9.
 */
public class ItemServiceImplTest {
    @Test
    public void getItemById() throws Exception {
       // 562379
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemService service = (ItemService)appliationContext.getBean("itemServiceImpl");
        long id  = 562379;
       String result = service.getItemDescById(id);
        System.out.println(result);
    }

    @Test
    public void getItemDescById() throws Exception {
    }

    @Test
    public void getItemParam() throws Exception {
    }

}