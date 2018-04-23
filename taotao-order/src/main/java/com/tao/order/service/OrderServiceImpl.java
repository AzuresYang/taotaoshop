package com.tao.order.service;

import com.tao.entity.ResponseResult;
import com.tao.mapper.TbOrderItemMapper;
import com.tao.mapper.TbOrderMapper;
import com.tao.mapper.TbOrderShippingMapper;
import com.tao.order.dao.JedisClient;
import com.tao.order.service.base.OrderService;
import com.tao.pojo.TbOrder;
import com.tao.pojo.TbOrderItem;
import com.tao.pojo.TbOrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/4/16.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    //创建一则订单，包括订单总体，订单明细，物流信息
    @Override
    public ResponseResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        //向订单插入记录
        //获得订单号，通过redis获得



        //插入物流信息
        String string = jedisClient.get(ORDER_GEN_KEY);
        if(StringUtils.isBlank(string))
        {
            jedisClient.set(ORDER_GEN_KEY,ORDER_INIT_ID);
        }
        String stringDetai = jedisClient.get(ORDER_DETAIL_GEN_KEY);
        if(StringUtils.isBlank(stringDetai))
        {
            jedisClient.set(ORDER_DETAIL_GEN_KEY,ORDER_INIT_ID);
        }
        //这一句是什么作用
        Long orderId = jedisClient.incr(ORDER_GEN_KEY);

        //补全pojo属性
        order.setOrderId(orderId+"");
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        //0：未评价 1：已评价
        order.setBuyerRate(0);
        //向订单表插入数据
        tbOrderMapper.insert(order);

        //插入订单明细
        for(TbOrderItem tbOrderItem:itemList)
        {
            //补全订单明细
            //取订单明细
            Long orderDetaiId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(orderDetaiId+"");
            tbOrderItem.setOrderId(orderId+"");
            tbOrderItemMapper.insert(tbOrderItem);
        }

        //补全物流信息
        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        tbOrderShippingMapper.insert(orderShipping);

        return ResponseResult.ok(orderId);
    }
}
