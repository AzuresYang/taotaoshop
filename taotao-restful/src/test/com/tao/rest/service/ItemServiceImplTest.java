package com.tao.rest.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItem;
import com.tao.pojo.TbItemDesc;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamItem;
import com.tao.rest.service.base.ItemService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/8.
 */
public class ItemServiceImplTest {
    @Test
    public void getItemBaseInfo() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemService service = (ItemService)appliationContext.getBean("itemServiceImpl");
        long id  = 562379;
        ResponseResult result = service.getItemDesc(id);
        if(result.getData() != null)
        {
            TbItemDesc itme = (TbItemDesc)result.getData();
            System.out.println("item info:"+itme.toString());
        }
        else{
            System.out.println("result data is null");
        }

    }

    @Test
    public void getItemDesc() throws Exception {
    }

    @Test
    public void getItemParam() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemService service = (ItemService)appliationContext.getBean("itemServiceImpl");
        long id  = 1188043;
        ResponseResult result = service.getItemParam(id);
        System.out.println("result status:"+result.getStatus());
        if(result.getData() != null)
        {
            TbItemParamItem itme = (TbItemParamItem)result.getData();
            System.out.println("item info:"+itme.toString());
        }
        else{
            System.out.println("result data is null");
        }
    }

}