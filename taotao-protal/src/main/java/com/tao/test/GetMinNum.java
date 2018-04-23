package com.tao.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 28029 on 2018/4/14.
 */
public class GetMinNum {
    public static void main(String[] args) {
    }

    public static String minNum(int[] numbers)
    {
        List<String> numberStr = new ArrayList<>();
        for(int i = 0; i < numbers.length ; i++)
            numberStr.add(numbers[i]+"");

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numbers.length;i++)
        {
            List<String> minList = getFirstMin(numberStr);
            if(minList == null)
                break;
            if(minList.size() == 1)
            {
                result.append(minList.get(0));
                numberStr.remove(minList.get(0));
            }
        }
        return result.toString();
    }

    private static List<String> getFirstMin(List<String> numbers)
    {
        if(numbers == null || numbers.size() <=0)
            return null;
        int min = numbers.get(0).charAt(0)-'0';
        for(String num:numbers)
        {
            if(num.charAt(0)-'0' < min)
                min = num.charAt(0)-'0';
        }

        List<String> resultList = new LinkedList<>();
        for(String num:numbers)
        {
            if(num.charAt(0)-'0' ==  min)
                resultList.add(num);
        }
        if(resultList.size() > 1)
        {
            //如果存在多位首位数字是一致的情况，从第二位开始对比，找到最小的一个数字
            resultList = getMinNumInMoreResult(resultList,numbers);

        }
        return resultList;
    }

    //如果存在多位首位数字是一致的情况，从第二位开始对比，找到最小的一个数字，
    //
    private static List<String> getMinNumInMoreResult(List<String>firstResult,
                                               List<String>origin)
    {
        List<String> list = new ArrayList<>();
        list.add(firstResult.get(0));
        return list;
    }
}
