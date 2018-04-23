package com.tao.rest.controller;

import com.tao.entity.ResponseResult;
import com.tao.rest.service.base.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/6.
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public ResponseResult contentCacheSync(@PathVariable Long contentCid) {
        ResponseResult result = null;
        try {
            result = redisService.syncContent(contentCid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
