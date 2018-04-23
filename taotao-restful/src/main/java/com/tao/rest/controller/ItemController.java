package com.tao.rest.controller;

import com.tao.entity.ResponseResult;
import com.tao.rest.service.base.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/8.
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public ResponseResult getItemBaseInfo(@PathVariable Long itemId) {
        ResponseResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public ResponseResult getItemDesc(@PathVariable Long itemId) {
        ResponseResult result = itemService.getItemDesc(itemId);
        return result;
    }

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public ResponseResult getItemParam(@PathVariable Long itemId) {
        ResponseResult result = itemService.getItemParam(itemId);
        return result;
    }

}
