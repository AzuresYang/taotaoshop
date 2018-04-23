package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItem;
import com.tao.protal.pojo.CartItem;
import com.tao.protal.pojo.Item;
import com.tao.protal.service.base.CartService;
import com.tao.utils.CookieUtils;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28029 on 2018/4/10.
 */
@Service
public class CartServiceImpl implements CartService{

    private Logger logger = LoggerFactory.getLogger(CartService.class);
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITME_INFO_URL}")
    private String ITEM_INFO_URL;

    private static final String CART_COOKIE_KEY = "TT_CART";

    @Override
    public ResponseResult addCartItem(Long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

        //查询是否有该商品
        TbItem item = getItemById(itemId);
        if(item == null)
        {
            return ResponseResult.build(400,"cannot find item:"+itemId,null);
        }
        //获取商品信息
        CartItem cartItem = null;
        //先从购物车商品列表中找出是否有该商品
        List<CartItem> itemList = getCartItemList(request);
        for(CartItem cItem:itemList)
        {
            //如果存在此商品
            if(cItem.getId() == itemId)
            {

                cItem.setNum(cItem.getNum()+num);
                cartItem = cItem;
                //该商品小于0的情况
                if(cItem.getNum() <=0)
                    itemList.remove(cItem);
                break;
            }
        }
        //原购物城中不存在此商品，往cookie中添加一个商品
        if(cartItem == null)
        {
            cartItem = new CartItem();
            cartItem.setNum(num);
            cartItem.setId(itemId);
            cartItem.setTitle(item.getTitle());
            cartItem.setImage(item.getImage()==null?"":item.getImage().split(",")[0]);
            cartItem.setPrice(item.getPrice());
            itemList.add(cartItem);
        }

        //把购物车列表写回cookie
        CookieUtils.setCookie(request,response,CART_COOKIE_KEY,JsonUtils.objectToJson(itemList),true);
        return ResponseResult.ok();
    }

    @Override
    public  List<CartItem> updateCartItem(Long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        //先从购物车商品列表中找出是否有该商品
        List<CartItem> itemList = getCartItemList(request);
        for(CartItem cItem:itemList)
        {
            //如果存在此商品
            if(cItem.getId() == itemId)
            {
                cItem.setNum(num);
                //该商品小于0的情况
                if(cItem.getNum() <=0)
                    itemList.remove(cItem);
                break;
            }
        }
        //把购物车列表写回cookie
        CookieUtils.setCookie(request,response,CART_COOKIE_KEY,JsonUtils.objectToJson(itemList),true);
       return itemList;
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        return getCartItemList(request);
    }

    @Override
    public ResponseResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        for(CartItem cartItem:itemList)
        {
            if(cartItem.getId() == itemId)
            {
                itemList.remove(cartItem);
                break;
            }
        }

        //重新写入cookie
        CookieUtils.setCookie(request,response,CART_COOKIE_KEY,JsonUtils.objectToJson(itemList),true);
        return ResponseResult.ok();
    }

    private List<CartItem> getCartItemList(HttpServletRequest request)
    {
        String cartJson = CookieUtils.getCookieValue(request,CART_COOKIE_KEY,true);
        if(cartJson == null)
        {
            return new ArrayList<>();
        }
        //把json转换成商品列表
        try{
            List<CartItem> list = JsonUtils.jsonToCollectionList(cartJson,CartItem.class);
            return list;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public TbItem getItemById(long itemId)
    {
        logger.info("get itemId:"+itemId);
        //根据上平id查询商品信息
        String itemJson = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
        logger.info("item result json:"+itemJson);
        ResponseResult responseResult = ResponseResult.formatToPojo(itemJson, TbItem.class);
        TbItem item = null;
        if(responseResult.getStatus() == 200)
        {
            item = (TbItem)responseResult.getData();
        }
        return item;
    }
}
