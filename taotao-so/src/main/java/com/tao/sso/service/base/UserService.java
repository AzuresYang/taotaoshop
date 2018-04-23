package com.tao.sso.service.base;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 28029 on 2018/4/9.
 */
public interface UserService {
    public ResponseResult checkData(String content, Integer type);
    ResponseResult createUser(TbUser user) throws Exception;
    ResponseResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    ResponseResult getUserByToken(String token);
}
