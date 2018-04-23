package com.tao.search.service;

import com.tao.entity.ResponseResult;
import com.tao.search.mapper.ItemMapper;
import com.tao.search.pojo.Item;
import com.tao.search.service.base.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 28029 on 2018/4/8.
 */
@Service
public class ItemServiceImpl implements ItemService{
    private static Logger logger  = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public ResponseResult importAllItems() {
        List<Item> list = itemMapper.getItemList();
        logger.info("Solr get item num:"+list.size());
        long size = 0;
        try{
            for(Item item:list)
            {

                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_des());

                //写入索引库
                solrServer.add(document);
                size++;
            }
            solrServer.commit();
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseResult.build(500, e.getMessage(),null);
        }
        System.out.println("succeeded commit size:"+size);
        return ResponseResult.ok();
    }
}
