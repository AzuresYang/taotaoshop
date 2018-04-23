package com.tao.protal.controller;

import com.tao.protal.pojo.SearchResult;
import com.tao.protal.service.base.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 28029 on 2018/4/8.
 */
@Controller
public class SearchContorller {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString,
                         @RequestParam(defaultValue="1")Integer page,
                         Model model)
    {
        SearchResult searchResult = searchService.search(queryString,page);

        //向页面传递参数
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";

    }
}
