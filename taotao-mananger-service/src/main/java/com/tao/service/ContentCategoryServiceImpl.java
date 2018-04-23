package com.tao.service;

import com.tao.entity.EUTreeNode;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbContentCategoryMapper;
import com.tao.pojo.TbContentCategory;
import com.tao.pojo.TbContentCategoryExample;
import com.tao.service.base.ContentCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/4/3.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    //获取广告分类列表
    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {


        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria a = example.createCriteria();
        a.andParentIdEqualTo(parentId);
        example.or(a);
        logger.info("ContentCategory get Category--id:"+parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        logger.info("result num:"+list.size());
        List<EUTreeNode> resultList = new ArrayList<>();
        for(TbContentCategory category: list)
        {
            EUTreeNode node = new EUTreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public ResponseResult insertCategory(long parentId, String name) {
        TbContentCategory category = new TbContentCategory();
        category.setCreated(new Date());
        category.setUpdated(new Date());
        category.setParentId(parentId);
        category.setName(name);

        //'状态。可选值:1(正常),2(删除)',
        category.setStatus(1);
        category.setSortOrder(1);

        TbContentCategory parentCat =  tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(parentCat == null || !parentCat.getIsParent())
             category.setIsParent(true);
        else
            category.setIsParent(false);

        tbContentCategoryMapper.insert(category);
        //这里填充了主键的值
        System.out.println("insert id is:"+category.getId());
        return ResponseResult.ok(category);
    }

    @Override
    public ResponseResult deleteCategory(long id) {
        logger.info("delete Category  -id:"+id);
        TbContentCategory categoryNode = tbContentCategoryMapper.selectByPrimaryKey(id);
        long parentId = categoryNode.getParentId();
        int delRe =  tbContentCategoryMapper.deleteByPrimaryKey(id);
        logger.info("delete Result num:"+ delRe);
        if(parentId !=0 ) {
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria a = example.createCriteria();
            a.andParentIdEqualTo(parentId);
            example.or(a);

            List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
            if (list.size() == 0) {
                TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
                if (parent != null) {
                    parent.setIsParent(false);
                    parent.setUpdated(new Date());
                    tbContentCategoryMapper.updateByPrimaryKey(parent);
                } else {
                    System.out.println("delete category - cant find parent id[" + parentId + "] is null");
                }
            }
        }
            return ResponseResult.ok();
    }

    @Override
    public ResponseResult updateCategory(long id, String name) {
        logger.info("update Category  -id:"+id);
        TbContentCategory categoryNode = tbContentCategoryMapper.selectByPrimaryKey(id);
        if(categoryNode == null)
        {
            logger.info("cannt find category id:"+id);
            ResponseResult responseResult = new ResponseResult();
            responseResult.setStatus(201);
            responseResult.setMsg("cannt find category id:"+id);
            responseResult.setData(null);
            return responseResult;
        }
        else{
            categoryNode.setName(name);
            categoryNode.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKeySelective(categoryNode);
            logger.info("update category id:"+id+" succeeded");
        }
        return ResponseResult.ok();

    }


}
