package com.tao.order.service.base;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbOrder;
import com.tao.pojo.TbOrderItem;
import com.tao.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 28029 on 2018/4/16.
 */
public interface OrderService {
      public ResponseResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
