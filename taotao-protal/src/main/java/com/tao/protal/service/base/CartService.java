package com.tao.protal.service.base;

import com.tao.entity.ResponseResult;
import com.tao.protal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.RectangularShape;
import java.util.List;

/**
 * Created by 28029 on 2018/4/10.
 */
public interface CartService {
    public ResponseResult addCartItem(Long itemId, int num , HttpServletRequest request, HttpServletResponse response);
    public  List<CartItem> updateCartItem(Long itemId, int num , HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    ResponseResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);

}
