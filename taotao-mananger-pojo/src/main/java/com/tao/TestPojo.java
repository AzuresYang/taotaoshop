package com.tao;
import com.tao.entity.EasyUIResult;
import com.tao.utils.FtpHelper;
/**
 * Created by 28029 on 2018/3/31.
 */
public class TestPojo {
    public static void main(String[] args) {
        String str = "10000000000000000000000000000000";
        str = "1111111111111111111111111111111";
        System.out.println(Integer.valueOf(str,2).toString());
    }
}
