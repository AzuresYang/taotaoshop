package com.tao.controller;

import com.tao.pojo.TbItemCat;
import com.tao.service.base.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

/**
 * Created by 28029 on 2018/3/23.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

   // @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/list")
    @ResponseBody
    public List categoryList(@RequestParam(value="id", defaultValue="0") Long parentId) throws Exception{
        List<Object> catList = new ArrayList();
        List<TbItemCat> list = itemCatService.getItemCatList(parentId);
        for(TbItemCat tbItemCat:list)
        {
            Map node = new HashMap();
            node.put("id", tbItemCat.getId());
            node.put("text", tbItemCat.getName());
            //如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
            node.put("state", tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);
        }

        return catList;
    }
}
