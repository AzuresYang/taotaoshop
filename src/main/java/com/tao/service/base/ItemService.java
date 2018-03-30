package com.tao.service.base;

import com.tao.entity.EasyUIResult;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItem;

/**
 * Created by 28029 on 2018/3/23.
 */
public interface ItemService {
    public TbItem getItemById(long id);
    public EasyUIResult getItemList(Integer page, Integer rows) throws Exception;
    public ResponseResult createItem(TbItem item, String desc, String itemParam);

    public ResponseResult insertItemParam(TbItem itme, String desc, String paramData);
}
