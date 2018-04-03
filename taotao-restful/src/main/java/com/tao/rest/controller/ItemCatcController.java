package com.tao.rest.controller;

import com.tao.rest.pojo.CatResult;
import com.tao.rest.service.base.ItemCatService;
import com.tao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * Created by 28029 on 2018/4/2.
 */
@Controller
public class ItemCatcController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value="/itemcat/list",
            produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getItemCatList(String callback)
    {
        CatResult catResult =itemCatService.getItemCatList();

        //把pojo转换成字符串
        String json= JsonUtils.objectToJson(catResult);
        String result = callback+"("+json+")";
        return result;
    }


    //使用jsonp的跨域回调方法
    @RequestMapping("/itemcat/list1")
    @ResponseBody
    public Object getItemCatList2(String callback)
    {
        CatResult catResult =itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
