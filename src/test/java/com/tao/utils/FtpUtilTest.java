package com.tao.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/25.
 */
public class FtpUtilTest {
    @Test
    public void uploadFile() throws Exception {
       // "F:\\图片\\红宝石.jpg","/home/ftpuser/www/images"
        //FtpUtil util = new FtpUtil("193.112.68.221","ftpuser","ftpuser2018",21);
       // boolean result = util.uploadFile("F:\\图片\\红宝石.jpg","/home/ftpuser/www/images");
        //System.out.println("upload "+result);
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
        System.out.println("当前时间：" + sdf.format(date));

    }

}