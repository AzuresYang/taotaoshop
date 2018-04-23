package com.tao.search.dao.base;

import com.tao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by 28029 on 2018/4/8.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception ;
}
