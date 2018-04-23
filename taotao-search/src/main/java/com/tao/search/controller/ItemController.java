package com.tao.search.controller;

import com.tao.entity.ResponseResult;
import com.tao.search.service.base.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/8.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/importall")
    @ResponseBody
    public ResponseResult importAllItems(){
        ResponseResult result = itemService.importAllItems();
        return result;
    }

}
