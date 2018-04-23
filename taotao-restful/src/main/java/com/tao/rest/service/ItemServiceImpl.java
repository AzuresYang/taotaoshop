package com.tao.rest.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbItemDescMapper;
import com.tao.mapper.TbItemMapper;
import com.tao.mapper.TbItemParamItemMapper;
import com.tao.mapper.TbItemParamMapper;
import com.tao.pojo.*;
import com.tao.rest.dao.JedisClient;
import com.tao.rest.service.base.ItemService;
import com.tao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 28029 on 2018/4/8.
 */
@Service
public class ItemServiceImpl implements ItemService {
    private Logger logger = LoggerFactory.getLogger("");
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private int REDIS_ITEM_EXPIRE;

    @Value("${REDIS_ITEM_KEY_SUF_BASE}")
    private String REDIS_ITEM_KEY_SUF_BASE;
    @Value("${REDIS_ITEM_KEY_SUF_DESC}")
    private String REDIS_ITEM_KEY_SUF_DESC;
    @Value("${REDIS_ITEM_KEY_SUF_PARAM}")
    private String REDIS_ITEM_KEY_SUF_PARAM;


    @Override
    public ResponseResult getItemBaseInfo(long itemId) {

        //先从缓存中获取
        logger.info("rest get item base info:"+itemId);
        try{
            String json = jedisClient.get(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_BASE);

            if(!StringUtils.isBlank(json)&& !json.equals("null"))
            {
                logger.info("get from redis:"+json);
                TbItem item = JsonUtils.jsonToPojo(json,TbItem.class);
                return ResponseResult.ok(item);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }



        TbItem item = tbItemMapper.selectByPrimaryKey(itemId);

        if(item == null)
        {
            logger.info("item is null");
            return ResponseResult.build(210,"cannot find item:"+itemId,null);
        }


        try{
            String toJson = JsonUtils.objectToJson(item);
            logger.info("get result:"+toJson);
            //设置key的值
            jedisClient.set(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_BASE,toJson);
            //设置有效期
            jedisClient.expire(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_BASE,REDIS_ITEM_EXPIRE);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseResult.ok(item);
    }

    @Override
    public ResponseResult getItemDesc(long itemId) {
        //先从缓存中获取
        logger.info("rest get item desc:"+itemId);
        try{
            String json = jedisClient.get(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_DESC);
            if(!StringUtils.isBlank(json)&&!json.equals("null"))
            {
                logger.info("get from redis:"+json);
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json,TbItemDesc.class);
                return ResponseResult.ok(itemDesc);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }


        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        if(itemDesc == null)
            return ResponseResult.build(210,"cannot find item:"+itemId,null);
        try{
            String toJson = JsonUtils.objectToJson(itemDesc);
            logger.info("get result:"+toJson);
            //设置key的值
            jedisClient.set(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_DESC,toJson);
            //设置有效期
            jedisClient.expire(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_DESC,REDIS_ITEM_EXPIRE);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseResult.ok(itemDesc);
    }

    @Override
    public ResponseResult getItemParam(long itemId) {
        //先从缓存中获取
        logger.info("rest get item param:"+itemId);
        try{
            String json = jedisClient.get(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_PARAM);
            if(!StringUtils.isBlank(json)&&!json.equals("null"))
            {
                logger.info("get from redis:"+json);
                TbItemParam itemParam = JsonUtils.jsonToPojo(json,TbItemParam.class);
                return ResponseResult.ok(itemParam);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        //根据商品id查询规格参数
        //设置查询条件
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list == null || list.size() <= 0)
        {
            logger.info("cannt find itemparam:"+itemId);
            return ResponseResult.build(210,"cannot find item:"+itemId,null);
        }


        TbItemParamItem itemParamItem = list.get(0);
        try{
            String toJson = JsonUtils.objectToJson(itemParamItem);
            logger.info("get result:"+toJson);
            //设置key的值
            jedisClient.set(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_PARAM,toJson);
            //设置有效期
            jedisClient.expire(REDIS_ITEM_KEY+itemId+REDIS_ITEM_KEY_SUF_PARAM,REDIS_ITEM_EXPIRE);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseResult.ok(itemParamItem);
    }
}
