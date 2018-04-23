package com.tao.rest.service.base;

import com.tao.entity.ResponseResult;

/**
 * Created by 28029 on 2018/4/6.
 */
public interface RedisService {
    public ResponseResult syncContent(Long contentCid) throws Exception;
}
