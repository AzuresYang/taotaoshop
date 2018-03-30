package com.tao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.entity.EasyUIResult;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbItemDescMapper;
import com.tao.mapper.TbItemMapper;
import com.tao.mapper.TbItemParamItemMapper;
import com.tao.pojo.*;
import com.tao.service.base.ItemService;
import com.tao.utils.IDUtil;
import com.tao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/3/23.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private TbItemMapper tbItemapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper  tbItemParamItemMapper;

    public TbItem getItemById(long id)
    {
        return tbItemapper.selectByPrimaryKey(id);
    }


    public EasyUIResult getItemList(Integer page, Integer rows) throws Exception
    {
        TbItemExample example = new TbItemExample();
        //设置分页
        PageHelper.startPage(page, rows);
        List<TbItem> list = tbItemapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult(total, list);
        return result;

    }
    @Override
    public ResponseResult createItem(TbItem item, String desc, String itemParam)
    {
        Long id = IDUtil.getItemId();

        item.setStatus((byte)1);
        item.setId(id);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        int insertRe = tbItemapper.insert(item);
        System.out.println("insert item:\n"+ JsonUtils.objectToJson(item));
        System.out.println("insert result:"+insertRe);
        //添加描述商品信息
        insertItemDesc(id,desc);

        ResponseResult result  = insertItemParamItem(id,itemParam);
        return result;


    }

    private ResponseResult insertItemDesc(long id, String desc)
    {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        return  ResponseResult.ok(null);
    }

    public ResponseResult insertItemParam(TbItem item, String desc, String paramData)
    {
        Long id = IDUtil.getItemId();

        item.setStatus((byte)1);
        item.setId(id);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        int insertRe = tbItemapper.insert(item);
        System.out.println("insert item:\n"+ JsonUtils.objectToJson(item));
        System.out.println("insert result:"+insertRe);
        return insertItemDesc(id,desc);
    }

    //插入商品规格参数
    private ResponseResult insertItemParamItem(Long itemId, String itemParam)
    {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        tbItemParamItemMapper.insert(itemParamItem);
        return ResponseResult.ok(null);
    }
}
