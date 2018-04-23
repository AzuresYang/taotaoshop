package com.tao.meituanTest.huya.fir;

import java.util.*;

/**
 * Created by 28029 on 2018/4/21.
 */
public class Main {
    public static void main(String[] args) {

        Set<String> dic = new HashSet<>();
        dic.add("中国");
        dic.add("直播");
        dic.add("游戏");
        dic.add("游戏直播");
        dic.add("综艺娱乐");
        dic.add("互动直播平台");

        Scanner sc = new Scanner(System.in);
        StringBuilder inputLine = new StringBuilder();
        while(sc.hasNextLine())
        {
            inputLine.append(sc.nextLine());
        }
        String input  = inputLine.toString();
        String str = "虎牙直播中国最大最好的互动直播平台。众多热门高清的游戏直播；上千款游戏，游戏大神齐聚虎牙。星光闪耀，顶尖赛事，综艺娱乐，美女秀场……不一样的精彩直播";
        String result = Main.getMM(dic,input);
        System.out.println(result);


    }
    public static String getMM(Set<String> dic, String str)
    {
        List<String> result = new LinkedList<>();
        if(str == null || str.equals(""))
            return "";
        int dicMaxLength = 6;
        StringBuilder sb = new StringBuilder();
        while(str.length() > 0)
        {
            int len = dicMaxLength;
            if(str.length() < len)
                len = str.length();

            boolean findContain = false;
            String word = str.substring(0,len);

            while(word.length() >1)
            {
                //if(word.length() == 1)
                //    break;
                if(dic.contains(word))
                {
                    findContain = true;
                    break;
                }else
                    word = word.substring(0,word.length()-1);
            }
            if(findContain)
            {
                result.add(word);
                sb.append(word+",");
            }

            str = str.substring(word.length());
        }
        return sb.toString().substring(0,sb.length()-1);
    }
}
