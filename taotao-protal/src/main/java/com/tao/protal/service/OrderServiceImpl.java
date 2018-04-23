package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.protal.pojo.Order;
import com.tao.protal.service.base.OrderService;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/4/17.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(Order order) {
        //调用创建订单服务之前补全用户信息。
        //从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
        //调用taotao-order的服务提交订单。
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL,
                JsonUtils.objectToJson(order));
        ResponseResult result = ResponseResult.format(json);
        if(result != null && result.getStatus() == 200)
        {
            Object orderId = result.getData();
            return orderId.toString();
        }
        return "";
    }
}
