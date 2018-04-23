package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbUser;
import com.tao.protal.service.base.UserService;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by 28029 on 2018/4/14.
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${SSO_BASE_URL}")
    public  String SSO_BASE_URL;
    @Value("${SSO_PAGE_LOGIN_URL}")
    public String SSO_PAGE_LOGIN_URL;
    @Value("${SSO_TOKEN2USER_URL}")
    private String SSO_TOKEN2USER_URL;

    @Override
    public TbUser getUserByToken(String token) {

        logger.info("Get user by token:"+token);
        String userJson = HttpClientUtil.doGet(SSO_BASE_URL+SSO_TOKEN2USER_URL+token);
        logger.info("user json"+userJson);
        ResponseResult responseResult = ResponseResult.formatToPojo(userJson,TbUser.class);
        if(responseResult.getStatus()==200)
        {
            TbUser tbUser = (TbUser)responseResult.getData();
            return tbUser;
        }
        return null;
    }
}
