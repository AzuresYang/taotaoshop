package com.tao.protal.service;

import com.tao.pojo.TbItem;
import com.tao.protal.pojo.Item;
import com.tao.protal.service.base.CartService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/14.
 */
public class CartServiceImplTest {
    @Test
    public void addCartItem() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        CartServiceImpl cartService = (CartServiceImpl)appliationContext.getBean("cartServiceImpl");
        TbItem item =cartService.getItemById((long)1205256);
        System.out.println(item.toString());

    }

    @Test
    public void getCartItemList() throws Exception {
    }

    @Test
    public void deleteCartItem() throws Exception {
    }

}