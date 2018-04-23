package com.tao.protal.controller;

import com.tao.entity.ResponseResult;
import com.tao.protal.pojo.CartItem;
import com.tao.protal.service.base.CartService;
import com.tao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 28029 on 2018/4/14.
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue = "1") Integer num,
                              HttpServletRequest request,
                              HttpServletResponse response)
    {
        cartService.addCartItem(itemId,num,request,response);
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        List<CartItem> list = cartService.getCartItemList(request,response);
        model.addAttribute("cartList",list);
        String json = JsonUtils.objectToJson(list);
        System.out.println("cart json:\n"+json);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}")
    public String updateCartItemNum(@PathVariable Long itemId,
                                    @RequestParam(defaultValue = "1") Integer num,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    Model model)
    {
        List<CartItem> list = cartService.updateCartItem(itemId,num,request,response);
        model.addAttribute("cartList",list);
        return "cart";
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    {
        cartService.deleteCartItem(itemId,request,response);
        return "cart";
    }

}
