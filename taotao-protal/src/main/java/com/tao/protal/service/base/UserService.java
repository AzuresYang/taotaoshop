package com.tao.protal.service.base;

import com.tao.pojo.TbUser;

/**
 * Created by 28029 on 2018/4/14.
 */
public interface UserService {
    TbUser getUserByToken(String token);
}
