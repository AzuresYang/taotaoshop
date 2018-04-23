package com.tao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.entity.EUDataGridResult;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbContentMapper;
import com.tao.pojo.TbContent;
import com.tao.pojo.TbContentCategoryExample;
import com.tao.pojo.TbContentExample;
import com.tao.service.base.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public EUDataGridResult getContentList(long categoryId, int page, int rows) {
        logger.info("get ContentList -  categoryid:"+categoryId+"  page:"+page+"  rows"+rows);

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria a = example.createCriteria();
        a.andCategoryIdEqualTo(categoryId);
        example.or(a);

        PageHelper.startPage(page, rows);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        EUDataGridResult result = new EUDataGridResult();

        PageInfo<TbContent>  pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        logger.info("select num:"+ result.getTotal());
        return result;
    }

    @Override
    public ResponseResult saveContent(TbContent content) {
        logger.info("insert content:title"+content.getTitle());
        tbContentMapper.insert(content);
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult updateContent(TbContent content) {
        logger.info("update content:title"+content.getTitle());
        content.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKey(content);
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult deleteContent(long contentId) {
        logger.info("delete content:id:"+contentId);
        tbContentMapper.deleteByPrimaryKey(contentId);
        return ResponseResult.ok();
    }
}
