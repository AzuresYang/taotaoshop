package com.tao.search.controller;

import com.tao.entity.ResponseResult;
import com.tao.search.pojo.SearchResult;
import com.tao.search.service.base.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/8.
 */

@Controller
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;

    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public ResponseResult search(@RequestParam("q") String queryString,
                                 @RequestParam(defaultValue = "1")Integer page,
                                 @RequestParam(defaultValue = "60")Integer rows)
    {
        //查询条件不能为空
        if(StringUtils.isBlank(queryString))
        {
            logger.info("queryString cannot be Blank");
            ResponseResult.build(400,"查询条件不能为空",null);
        }

        SearchResult searchResult = null;

//        try {
//            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
//            searchResult = searchService.search(queryString, page, rows);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseResult.build(500, e.getMessage(),null);
//        }
        try {
            searchResult = searchService.search(queryString,page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.build(500, e.getMessage(),null);
        }

        return ResponseResult.ok(searchResult);
    }

}
