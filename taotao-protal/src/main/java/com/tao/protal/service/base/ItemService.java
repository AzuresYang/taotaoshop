package com.tao.protal.service.base;

import com.tao.protal.pojo.ItemInfo;

/**
 * Created by 28029 on 2018/4/8.
 */
public interface ItemService {

    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
