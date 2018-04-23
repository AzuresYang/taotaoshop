package com.tao.protal.service.base;

import com.tao.protal.pojo.SearchResult;

/**
 * Created by 28029 on 2018/4/8.
 */
public interface SearchService {
    SearchResult search(String queryString, int page);
}
