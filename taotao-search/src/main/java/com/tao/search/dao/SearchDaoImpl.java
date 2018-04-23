package com.tao.search.dao;

import com.tao.search.dao.base.SearchDao;
import com.tao.search.pojo.Item;
import com.tao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2018/4/8.
 */
@Repository
public class SearchDaoImpl implements SearchDao{

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {

        //返回值
        SearchResult result = new SearchResult();
        QueryResponse queryResponse = solrServer.query(query);

        SolrDocumentList documents = queryResponse.getResults();

        result.setRecordCount(documents.getNumFound());
        //商品列表
        List<Item> itemList  = new ArrayList<>();

        //高亮显示
        Map<String, Map<String, List<String>>> hightlighting = queryResponse.getHighlighting();

        //取商品列表
        for(SolrDocument solrDocument: documents)
        {
            //商品对象存储
            Item item = new Item();
            List<String> list = hightlighting.get(solrDocument.get("id")).get("item_title");

            String title = "";
            if(list != null && list.size() >0)
            {
                title = list.get(0);
            }else{
                title = (String) solrDocument.get("item_title");
            }
            item.setId((String)solrDocument.get("id"));
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            //添加的商品列表
            itemList.add(item);
        }

        result.setItemList(itemList);
        return result;
    }
}
