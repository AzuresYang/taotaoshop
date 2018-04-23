package com.tao.meituanTest.huya.sec;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 28029 on 2018/4/21.
 */
public class Main {
    public static void main(String[] args) {
        Map<Character,Character> map = new HashMap<>();
        map.put('1','2');
        map.put('2','4');
        map.put('3','1');
        map.put('4','5');
        map.put('5','3');

        String input = "1,4,3,2,1,5";
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        String result = Main.encode(map,input);
        System.out.println(result);
    }

    public static String encode(Map<Character, Character> map, String str)
    {
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i<sb.length();i+=2)
        {
            Character old = sb.charAt(i);
            Character n = map.get(old);
            sb.setCharAt(i,n);
        }
        return sb.toString();
    }
}
