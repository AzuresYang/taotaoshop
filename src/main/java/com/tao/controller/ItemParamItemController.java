package com.tao.controller;

import com.tao.service.base.ItemParamItemService;
import com.tao.service.base.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 28029 on 2018/3/29.
 */
@Controller
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemId}")
    public String getItemParamItemById(@PathVariable Long itemId, Model model) {
        String itemParamItem = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("param1", itemParamItem);
        return "item";
    }


}
