package com.tao.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamItem;
import com.tao.service.base.ItemParamService;
import com.tao.utils.JsonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/28.
 */
public class ItemParamServiceImplTest {
    @Test
    public void getItemParamByCid() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemParamService service = (ItemParamService) appliationContext.getBean("itemParamServiceImpl");
        ResponseResult result= service.getItemParamByCid((long)6);


        System.out.println(result.getData());

    }

}