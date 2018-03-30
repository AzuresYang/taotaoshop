package com.tao.utils;

import com.fasterxml.jackson.databind.JavaType;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by 28029 on 2018/3/29.
 */
public class JsonUtilsTest {
    @Test
    public void jsonToList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String ,String > map = new HashMap<>();
        map.put("品牌","华为");
        map.put("价格","11");

        Map<String ,String > map2 = new HashMap<>();
        map.put("处理器","754");
        map.put("显示屏","5寸");

        String str = mapper.writeValueAsString(map);
        System.out.println("map"+str);
        List<Map> list = new ArrayList<>();
        list.add(map);
        list.add(map2);

        str = mapper.writeValueAsString(map);
        System.out.println("list:"+str);

        String testStr="[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"苹果（Apple）\"},{\"k\":\"型号\",\"v\":\"iPhone 6 A1586\"},{\"k\":\"颜色\",\"v\":\"金色\"},{\"k\":\"上市年份\",\"v\":\"2014\"}]}]";

       // JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,Map.class);
        // List<Map> lst = JsonUtils.jsonToList(testStr,Map.class);
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class,Map.class);
        List<Map> lst= (List<Map>)mapper.readValue(testStr,javaType);

        for(Map aMap:lst)
        {
           for(Object s:aMap.keySet())
           {
               System.out.println("key:"+s.toString()+"   value:"+aMap.get(s).toString());
           }
        }
    }

}