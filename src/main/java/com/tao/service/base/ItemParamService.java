package com.tao.service.base;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamItem;

/**
 * Created by 28029 on 2018/3/28.
 */
public interface ItemParamService {
   public ResponseResult getItemParamByCid(long cid);
    public TbItemParamItem getItemParamItemById(long id);
    public ResponseResult insertItemParam(TbItemParam itemParam);
}
