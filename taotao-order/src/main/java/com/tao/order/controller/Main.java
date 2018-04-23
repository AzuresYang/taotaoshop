package com.tao.order.controller;


import java.util.Scanner;

/**
 * Created by 28029 on 2018/4/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long count = count2(n);

        System.out.println(count);


    }

    public static long count1(long n)
    {
        long count = 1;

        for(long i = 1 ; i <= n; i+=2)
        {
            String binaryStr = Long.toBinaryString(i);
            if(isHuiWen(binaryStr))
            {

                count++;
            }
        }
        return count;
    }

    public static long count2(long n)
    {


        long count = 1;
        long i = 1;
        if(n > 30000000)
        {
            count = 11419;
            i = 30000001;
        }
        for( ; i <= n; i+=2)
        {
            String binaryStr = Long.toBinaryString(i);
            if(isHuiWen(binaryStr))
            {
                count++;
            }
        }
        return count;
    }
    public static boolean isHuiWen(String str)
    {
        int left = 0;
        int right = str.length()-1;
        while(left < right)
        {
            if(str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;

    }

}
