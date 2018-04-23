package com.tao.rest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import com.tao.entity.EUDataGridResult;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbContentMapper;
import com.tao.pojo.TbContent;
import com.tao.pojo.TbContentExample;
import com.tao.rest.dao.JedisClient;
import com.tao.rest.service.base.ContentService;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.HttpUtils;
import com.tao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/4/3.
 */
@Service
public class ContentServiceImpl implements ContentService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private  String INDEX_CONTENT_REDIS_KEY;

    @Value("${REST_CONTENT_SYNC_URL}")
    private  String REST_CONTENT_SYNC_URL;

    @Value("${REST_BASE_URL}")
    private  String REST_BASE_URL;

    @Override
    public List<TbContent> getContentList(long categoryId) {
        System.out.println("logger class:"+logger.getClass());
        logger.info("get ContentList -  categoryid:"+categoryId);
        logger.debug("debug test");
        //从缓存中获取内容
        try{

            String cache = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,categoryId+"");
            if(!StringUtils.isBlank(cache))
            {
                logger.info("Get Content from redis:"+cache);
                List<TbContent> resultList = JsonUtils.jsonToCollectionList(cache,TbContent.class);
                return resultList;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria a = example.createCriteria();
        a.andCategoryIdEqualTo(categoryId);
        example.or(a);

        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        try{

            //把list转换成字符串
            String caheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,categoryId+"",caheString);
            logger.info("Set Categroy To Redis:"+caheString);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        logger.info("select num:"+ list.size());
        return list;
    }

    @Override
    public ResponseResult saveContent(TbContent content) {
        logger.info("insert content:title"+content.getTitle());
        tbContentMapper.insert(content);
        //添加缓存同步
        try{
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getId());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult updateContent(TbContent content) {
        logger.info("update content:title"+content.getTitle());
        content.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKey(content);

        //添加缓存同步
        try{
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getId());
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult deleteContent(long contentId) {
        logger.info("delete content:id:"+contentId);
        tbContentMapper.deleteByPrimaryKey(contentId);

        //添加缓存同步
        try{
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+contentId);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseResult.ok();
    }
}
