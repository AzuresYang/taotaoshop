package com.tao.controller;

import com.tao.pojo.TbUser;
import com.tao.pojo.TbUserExample;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/21.
 */
public class DaoTestTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hello() throws Exception {
        TbUser user = new TbUser();
        TbUserExample example = new TbUserExample();
        example.setDistinct(true);
        TbUserExample.Criteria a = example.createCriteria();
        a.andIdEqualTo((long)7);
        example.or(a);
        long id = 7;
        //user = daot.tbUserMapper.selectByPrimaryKey(id);
        //user = (TbUser)( daot.tbUserMapper.selectByExample(example).get(0));
        System.out.println(user.getUsername());



    }

}