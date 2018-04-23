package com.tao.order.pojo;

import com.tao.pojo.TbOrder;
import com.tao.pojo.TbOrderItem;
import com.tao.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 28029 on 2018/4/17.
 */
public class Order extends TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;
    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }
    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
