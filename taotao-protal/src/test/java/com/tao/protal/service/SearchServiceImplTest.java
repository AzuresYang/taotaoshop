package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.protal.pojo.ItemInfo;
import com.tao.protal.service.base.ItemService;
import com.tao.protal.service.base.SearchService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/8.
 */
public class SearchServiceImplTest {
    @Test
    public void search() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemService service = (ItemService) appliationContext.getBean("itemServiceImpl");
        //562379
        long id = 100;
        ItemInfo item = service.getItemById(id);
        System.out.println(item.toString());

    }

}