package com.tao.utils;

import java.util.Random;


/**
 * Created by 28029 on 2018/3/25.
 */
public class IDUtil {
    public static String getImageName() {
        long mills = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        String name = mills+String.format("%03d",end3);
        return name;
    }
    public static Long getItemId() {
        long mills = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(99);
        String name = mills+String.format("%02d",end3);
        return Long.parseLong(name);
    }
}
