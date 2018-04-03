package com.tao.service;

import com.tao.entity.ResponseResult;
import com.tao.mapper.TbItemParamItemMapper;
import com.tao.mapper.TbItemParamMapper;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamExample;
import com.tao.pojo.TbItemParamItem;
import com.tao.service.base.ItemParamService;
import com.tao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 28029 on 2018/3/28.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    public TbItemParamItem getItemParamItemById(long id)
    {
        TbItemParamItem result = tbItemParamItemMapper.selectByPrimaryKey(id);
        System.out.println("data:"+result.getParamData());
        return result;
    }
    @Override
    public ResponseResult getItemParamByCid(long cid)
    {

        ResponseResult responseResult = new ResponseResult();
        System.out.println("查询ItemParam-cid:"+cid);
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria a = example.createCriteria();
        a.andItemCatIdEqualTo(cid);
        example.or(a);
        List<TbItemParam> results= tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(results.size() <=0 )
        {

            responseResult.setStatus(404);
            responseResult.setMsg("找不到类目参数："+cid);
            return ResponseResult.ok(null);
        }else
        {
            TbItemParam param = results.get(0);
            responseResult = ResponseResult.ok(param);
        }

        System.out.println("getItemParamByCid--data\n"+ JsonUtils.objectToJson(responseResult));
        return responseResult;
    }
    @Override
    public ResponseResult insertItemParam(TbItemParam itemParam)
    {

        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        tbItemParamMapper.insert(itemParam);
        System.out.println("添加一个ItemParam:\ncat_id:"+itemParam.getItemCatId()+"\n data:"+itemParam.getParamData());
        return ResponseResult.ok(null);

    }
}
