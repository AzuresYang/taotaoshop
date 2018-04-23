package com.tao.search.service.base;

import com.tao.search.pojo.SearchResult;

/**
 * Created by 28029 on 2018/4/8.
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
