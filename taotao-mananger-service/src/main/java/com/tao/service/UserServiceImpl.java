package com.tao.service;

import com.tao.mapper.TbUserMapper;
import com.tao.pojo.TbUser;
import com.tao.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/3/27.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public int createUser(TbUser user)
    {
        return tbUserMapper.insert(user);
    }

}
