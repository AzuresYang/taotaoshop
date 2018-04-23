package com.tao;

import com.tao.pojo.TbUser;
import com.tao.service.base.ContentCategoryService;
import com.tao.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 28029 on 2018/3/31.
 */
public class TestJuhe {
    public static void main(String[] args) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("hei");
        System.out.println(tbUser.getUsername());
        String name  = IDUtil.getImageName();
        System.out.println(name);
    }

    public void sayHello(){
        System.out.println("hello test juhe");
    }

}
