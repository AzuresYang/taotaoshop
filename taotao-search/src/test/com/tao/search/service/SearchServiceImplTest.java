package com.tao.search.service;

import com.tao.search.pojo.Item;
import com.tao.search.pojo.SearchResult;
import com.tao.search.service.base.SearchService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/14.
 */
public class SearchServiceImplTest {
    @Test
    public void search() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
       SearchService service = (SearchService)appliationContext.getBean("searchServiceImpl");
       String query = "4G";
       SearchResult result = service.search(query,1,10);
       List<Item> list =  result.getItemList();

       for(Item item:list)
       {
           System.out.println(item.toString());
       }

    }

}