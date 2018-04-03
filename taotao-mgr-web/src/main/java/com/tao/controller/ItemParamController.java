package com.tao.controller;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItemParam;
import com.tao.service.base.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/3/28.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
   @Autowired
    private ItemParamService itemParamService;

   @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public ResponseResult getItemParamByCid(@PathVariable Long itemCatId)
   {
       System.out.println("查询ItemParam-cid:"+itemCatId);
       ResponseResult result = itemParamService.getItemParamByCid(itemCatId);
       return result;
   }
   @RequestMapping("/save/{cid}")
    @ResponseBody
    public ResponseResult insertItemParam(@PathVariable long cid, String paramData)
   {
        //创建POJO对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);

        ResponseResult result =  itemParamService.insertItemParam(itemParam);
        return result;
   }

}
