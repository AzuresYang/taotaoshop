package com.tao;

import com.tao.pojo.TbUser;

/**
 * Created by 28029 on 2018/3/31.
 */
public class TestJuhe {
    public static void main(String[] args) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("hei");
        System.out.println(tbUser.getUsername());
    }
    public void sayHello(){
        System.out.println("hello test juhe");
    }

}
