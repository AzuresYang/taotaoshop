package com.tao.pojo;

import java.util.Scanner;

/**
 * Created by 28029 on 2018/4/5.
 */
public class ForLeetCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        String line = sc.nextLine();
        if(line.length() >= 4)
            str = line.substring(0,3);
        else
            str  = line;
        System.out.println(CountIndex.countIndex(str));
    }
}

class CountIndex{


    public static long countIndex(String str)
    {
        long[] base = new long[4];
        base[3] = 1;
        for(int i = 2; i >=0; i--)
        {
            base[i] = base[i+1]*25+1;
        }
        long count = 0;
        for(int i = 0; i < str.length();i++)
        {
            count += (str.charAt(i)-'a')*base[i]+1;
        }
        return count-1;
    }


}
