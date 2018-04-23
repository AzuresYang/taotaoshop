package com.tao.order.controller;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/4/16.
 */
public class HelloTest {
    @Test
    public void hello() throws Exception {
        for(int i = 0; i < 100;i++)
        {
            String id= UUID.randomUUID().toString();
            System.out.println(id);
        }

    }

}