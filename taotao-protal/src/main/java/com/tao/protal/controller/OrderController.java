package com.tao.protal.controller;

import com.tao.pojo.TbUser;
import com.tao.protal.pojo.CartItem;
import com.tao.protal.pojo.Order;
import com.tao.protal.service.base.CartService;
import com.tao.protal.service.base.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/4/17.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response,Model model)
    {
        List<CartItem> list = cartService.getCartItemList(request,response);
        //传递给页面
        model.addAttribute("cartList",list);
        return "order-cart";
    }


    @RequestMapping("/create")
    public String createOrder(Order order, Model model, HttpServletRequest request) {
        try {
            //从Request中取用户信息
            TbUser user = (TbUser)request.getAttribute("user");
            //在Order对象中补全用户信息
            order.setUserId(user.getId());
            order.setBuyerNick(user.getUsername());

            //调用服务
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId",orderId);
            model.addAttribute("payment",order.getPayment());
            //预计到达时间
            Calendar now = Calendar.getInstance();

            Date date = new Date();
            now.setTime(date);
            now.set(Calendar.DATE,now.get(Calendar.DATE)+3);
            Date threeDay = now.getTime();
            model.addAttribute("date",formatter.format(threeDay));
            return "success";

        }catch(Exception e)
        {
            e.printStackTrace();
            model.addAttribute("message","创建订单出错，请稍后重试");
            return "error/exception";

        }
    }
}
