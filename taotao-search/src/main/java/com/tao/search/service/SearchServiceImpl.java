package com.tao.search.service;

import com.tao.search.dao.base.SearchDao;
import com.tao.search.pojo.SearchResult;
import com.tao.search.service.base.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/4/8.
 */
@Service
public class SearchServiceImpl implements SearchService{
    private Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        logger.info("query:"+queryString+"    set page:"+page+"   set rows"+rows);

        //创建查询对象
        SolrQuery query = new SolrQuery();

        query.setQuery(queryString);

        //设置分页
        query.setStart((page-1)*rows);
        query.setRows(rows);

        //设置默认搜索域
        query.set("df", "item_keywords");
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        //执行查询
        SearchResult searchResult = searchDao.search(query);

        //查询结果总页数
        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount / rows;
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);
        logger.info("search result - resultNum:"+recordCount);
        return searchResult;
    }
}
