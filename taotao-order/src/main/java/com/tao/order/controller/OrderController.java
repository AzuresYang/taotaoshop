package com.tao.order.controller;

import com.tao.entity.ResponseResult;
import com.tao.order.pojo.Order;
import com.tao.order.service.base.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/17.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult createOrder(@RequestBody Order order){
        try{
            ResponseResult result = orderService.createOrder(order,
                    order.getOrderItems(), order.getOrderShipping());
            return result;
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseResult.build(500,e.getMessage(),null);
        }
       // ResponseResult result =

    }
}
