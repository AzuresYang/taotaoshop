package com.tao.search.service;

import com.tao.search.pojo.Item;
import com.tao.search.pojo.SearchResult;
import com.tao.search.service.base.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/7.
 */
public class ServiceTestTest {
    @Test
    public void getList() throws Exception {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ServiceTest serviceTest = (ServiceTest)appliationContext.getBean("serviceTest");
        List<Item> list = serviceTest.getList();
        System.out.println("list size:"+list.size());
    }

    @Test
    public void testSolr() {
        HttpSolrServer server = new HttpSolrServer("http://193.112.68.221:8080/solr");

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "solrJ Test");
        document.addField("item_title", "solrJ 测试Item");
        document.addField("item_price", 111);
        try {
            server.add(document);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQuery() throws SolrServerException {
        System.out.println("start query test");
        SolrServer server = new HttpSolrServer("http://193.112.68.221:8080/solr");
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(20);
        query.setRows(50);

        QueryResponse response = server.query(query);

        //取查询结果
        SolrDocumentList solrDocuments = response.getResults();
        System.out.println("Solr query result nums:"+solrDocuments.getNumFound());
        int num = 0;
        for(SolrDocument document: solrDocuments)
        {
            System.out.println("id:"+document.get("id"));
            System.out.println(document.get("item_title"));
            System.out.println(document.get("item_price"));
            System.out.println(document.get("item_image"));
            num++;
        }
        System.out.println("real num is:"+num);

    }

    @Test
    public void testSearch(){
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");

        SearchService service = (SearchService)appliationContext.getBean("searchServiceImpl");

        try {
            SearchResult result = service.search("金立",1,50);

            List<Item>  list = result.getItemList();
            for(Item item:list)
            {
                System.out.println(item.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("query error");
        }

    }

}