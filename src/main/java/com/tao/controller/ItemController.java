package com.tao.controller;

import com.tao.entity.EasyUIResult;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItem;
import com.tao.service.base.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/3/23.
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @RequestMapping("/list")
    //设置相应的内容为json数据
    @ResponseBody
    public EasyUIResult getItemlist(@RequestParam(defaultValue="1")Integer page,
                                    @RequestParam(defaultValue="30")Integer rows) throws Exception {
        //查询商品列表
        EasyUIResult result = itemService.getItemList(page, rows);

        return result;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    private ResponseResult saveItem(TbItem item)
    {
        ResponseResult result = itemService.createItem(item);
        return result;
    }




}
