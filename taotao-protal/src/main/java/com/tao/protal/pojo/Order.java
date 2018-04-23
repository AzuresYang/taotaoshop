package com.tao.protal.pojo;

import java.util.List;

import com.tao.pojo.TbOrder;
import com.tao.pojo.TbOrderItem;
import com.tao.pojo.TbOrderShipping;

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

    @Override
    public String toString() {
        return "Order{" +
                "orderItems=" + orderItems +
                ", orderShipping=" + orderShipping +
                '}';
    }
}
