package com.tao.rest.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tao.entity.ResponseResult;
import com.tao.rest.dao.JedisClient;
import com.tao.rest.service.base.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/4/6.
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Override
    public ResponseResult syncContent(Long contentCid) throws Exception {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.build(500, e.getMessage(),null);
        }
        return  ResponseResult.ok();
    }
}
