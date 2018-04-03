package com.tao.service;

import com.tao.mapper.TbUserMapper;
import com.tao.pojo.TbUser;
import com.tao.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/3/22.
 */
@Service
public class UserItem {
    @Autowired
    private TbUserMapper tbUserMapper;
   public int getCount()
   {
       TbUser user = new TbUser();
       TbUserExample example = new TbUserExample();
       example.setDistinct(true);
       TbUserExample.Criteria a = example.createCriteria();
       a.andIdEqualTo((long)7);
       example.or(a);
       return tbUserMapper.countByExample(example);
   }

   public TbUser getUserById(long id)
   {
       return tbUserMapper.selectByPrimaryKey(id);
   }

}
