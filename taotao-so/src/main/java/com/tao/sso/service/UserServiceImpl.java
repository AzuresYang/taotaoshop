package com.tao.sso.service;

import com.tao.entity.ResponseResult;
import com.tao.mapper.TbUserMapper;
import com.tao.pojo.TbUser;
import com.tao.pojo.TbUserExample;
import com.tao.sso.dao.JedisClient;
import com.tao.sso.service.base.UserService;
import com.tao.utils.CookieUtils;
import com.tao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 28029 on 2018/4/9.
 */
@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private int REDIS_SESSION_EXPIRE;
    //检测数据是否可用
    @Override
    public ResponseResult checkData(String content, Integer type) {
        logger.info("check Data:content:"+content+"   type:"+type);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria a = example.createCriteria();
        if(type == 1)
            a.andUsernameEqualTo(content);
        else if(type == 2)
            a.andPhoneEqualTo(content);
        else
            a.andEmailEqualTo(content);

        example.or(a);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        boolean result = false;

        if(list == null || list.size()==0)
        {
            result = true;
        }
        logger.info("check result:"+ result);

        return ResponseResult.ok(result);
    }

    @Override
    public ResponseResult createUser(TbUser user) throws Exception{
        logger.info("try create user:"+user.getUsername());
        user.setUpdated(new Date());
        user.setCreated(new Date());

        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        try
        {
            tbUserMapper.insert(user);
            logger.info("create user ["+user.getUsername()+"] succceed");
        }catch(Exception e)
        {
            logger.info("create user ["+user.getUsername()+"] faild");
            throw new Exception(e);
        }

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult userLogin(String username,
                                    String password,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria a = example.createCriteria();
        a.andUsernameEqualTo(username);
        example.or(a);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        logger.info("user login:"+username);

        if(null == list || list.size() == 0)
        {
            logger.info("login failed:no user");
            return ResponseResult.build(400,"用户名或密码错误",null);

        }


        TbUser user =  list.get(0);

        //对比密码

        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword()))
        {
            logger.info("login failed:no user");
            return ResponseResult.build(400,"用户名或密码错误",null);
        }


        //生成token
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
        jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token,REDIS_SESSION_EXPIRE);

        //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失败
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);
        logger.info("login susscced,token is"+token);
        //返回token
        return ResponseResult.ok(token);
    }

    @Override
    public ResponseResult getUserByToken(String token) {
        //更据token从Redis中查询用户信息,如果不存在这个信息，说明没有登录或者登录过期了
        String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);

        //判断是否为空
        if(StringUtils.isBlank(json))
        {
            logger.info("token is outtime");
            return ResponseResult.build(400,"此SESSION已经过期，请重新登录",null);
        }


        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token,REDIS_SESSION_EXPIRE);

        //返回用户信息
        TbUser user = (TbUser) JsonUtils.jsonToPojo(json,TbUser.class);
        logger.info("get user by token:"+user.getUsername());
        return ResponseResult.ok(user);

    }

}
