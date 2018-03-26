package com.tao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.entity.EasyUIResult;
import com.tao.entity.ResponseResult;
import com.tao.mapper.TbItemMapper;
import com.tao.pojo.TbItem;
import com.tao.pojo.TbItemExample;
import com.tao.service.base.ItemService;
import com.tao.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/3/23.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    TbItemMapper tbItemapper;
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
    public ResponseResult createItem(TbItem item)
    {
        Long id = IDUtil.getItemId();

        item.setStatus((byte)1);
        item.setId(id);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        tbItemapper.insert(item);

        return ResponseResult.ok(null);
    }
}
