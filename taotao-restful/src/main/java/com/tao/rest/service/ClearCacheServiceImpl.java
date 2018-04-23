package com.tao.rest.service;

import com.tao.entity.ResponseResult;
import com.tao.rest.dao.JedisClient;
import com.tao.rest.service.base.ClearCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/4/6.
 */
@Service
public class ClearCacheServiceImpl implements ClearCacheService{
    @Autowired
    private JedisClient cluster;
    @Value("${TB_CONTENT_KEY}")
    private String TB_CONTENT_KEY;

    @Override
    public ResponseResult clearContentCache(Long cid) throws Exception {
        //删除Cid相应的内容
        cluster.hdel(TB_CONTENT_KEY,cid.toString());
        return ResponseResult.ok();
    }

}
