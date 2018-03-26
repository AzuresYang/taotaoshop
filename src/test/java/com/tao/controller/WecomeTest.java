package com.tao.controller;

import com.github.pagehelper.PageHelper;
import com.tao.mapper.TbItemMapper;
import com.tao.pojo.TbItem;
import com.tao.pojo.TbItemExample;
import com.tao.pojo.TbUserExample;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/22.
 */
public class WecomeTest {
    @Autowired
    Wecome wecome;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sion() throws Exception {
        wecome.sion();
    }

    @Test
    public void testPageHelper() throws Exception{
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        TbItemMapper mapper = (TbItemMapper)appliationContext.getBean(TbItemMapper.class);
        TbItemExample ex = new TbItemExample();
        TbItemExample.Criteria a = ex.createCriteria();
        a.andPriceEqualTo((long)49900);
        ex.or(a);
        PageHelper.startPage(1,10);
        List<TbItem> list = mapper.selectByExample(ex);
        System.out.println("size="+list.size());
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).getTitle());
        }
    }

}