package com.tao.rest.service.base;

import com.tao.entity.ResponseResult;

/**
 * Created by 28029 on 2018/4/8.
 */
public interface ItemService {
    ResponseResult getItemBaseInfo(long itemId);
    ResponseResult getItemDesc(long itemId);
    ResponseResult getItemParam(long itemId);
}
