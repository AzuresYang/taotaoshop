package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbContent;
import com.tao.protal.service.base.ContentService;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2018/4/4.
 */
@Service
public class ContentServiceImpl implements ContentService{


    private static  Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;
    @Override
    public String getContentList() {
        logger.info("getContentList--result:");
        //调用服务层的服务
        String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
       logger.info(result);

        try{
            ResponseResult responseResult = ResponseResult.formatToList(result, TbContent.class);

            //获取内容列表
            List<TbContent> list = (List<TbContent>) responseResult.getData();
            List<Map> resultList = new ArrayList<>();

            for(TbContent tbContent: list)
            {
                Map map = new HashMap<>();
                map.put("src", tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);

            }
            return JsonUtils.objectToJson(resultList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
