package com.tao.service.base;

import com.tao.service.ItemParamItemServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/29.
 */
public class ItemParamItemServiceImplTest {
    @Test
    public void getItemParamByItemId() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemParamItemService service =
                (ItemParamItemService) appliationContext.getBean("itemParamItemServiceImpl");
        String str = service.getItemParamByItemId((long)48);
        System.out.println(str);
    }

}