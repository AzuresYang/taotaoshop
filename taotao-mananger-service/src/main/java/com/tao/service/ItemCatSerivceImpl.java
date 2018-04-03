package com.tao.service;

import com.tao.mapper.TbItemCatMapper;
import com.tao.pojo.TbItemCat;
import com.tao.pojo.TbItemCatExample;
import com.tao.service.base.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 28029 on 2018/3/23.
 */
@Service
public class ItemCatSerivceImpl implements ItemCatService{
    @Autowired
    private TbItemCatMapper itemCatMapper;


    public List<TbItemCat> getItemCatList(Long parentId) throws Exception
    {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria a = example.createCriteria();
        //设置查询条件
        a.andParentIdEqualTo(parentId);
       List<TbItemCat>  list = itemCatMapper.selectByExample(example);
       return list;
    }
}
