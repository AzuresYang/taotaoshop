package com.tao.rest.service;

import com.tao.rest.pojo.CatResult;
import com.tao.rest.service.base.ItemCatService;
import com.tao.utils.JsonUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/2.
 */
public class ItemCatServiceImplTest {
    @Test
    public void getItemCatList() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");

        ItemCatService service = (ItemCatService)appliationContext.getBean("itemCatServiceImpl");
        CatResult catResult =service.getItemCatList();

        //把pojo转换成字符串
        String result= JsonUtils.objectToJson(catResult);
        System.out.println(result);
    }


}