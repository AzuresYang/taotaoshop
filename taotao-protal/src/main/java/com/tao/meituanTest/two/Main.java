package com.tao.meituanTest.two;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by 28029 on 2018/4/20.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        for(int i =0 ; i < num;i++)
        {
            long n = sc.nextLong();
            System.out.println(Main.getNum(n));
        }

    }

    public static String getNum2(long n)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n;i++)
        {
            sb.append(i+"");
        }
        return sb.toString();
    }
    public static String getNum(long n)
    {
        if(n <10)
            return ""+n;
        BigInteger result = BigInteger.ZERO;
        long base = 1;
        long baseWeiNum = 1;
        long tempN = n;
        while(tempN / (base*10) != 0)
        {
            //System.out.println("temN:"+tempN);
           // System.out.println("base:"+base);
            Long temp = base*9*baseWeiNum;
            BigInteger bTemp=new BigInteger(temp.toString());
           // System.out.println("b:"+bTemp.toString());
            result = new BigInteger(result.add(bTemp).toString());
            //System.out.println("result:"+  result);
            base*=10;
            baseWeiNum++;
        }

        Long yvshu = n % base+1;
        BigInteger yvWei = new BigInteger(yvshu.toString());
        BigInteger  bBaseWeiNum =new BigInteger(""+ baseWeiNum);
        yvWei = new BigInteger(yvWei.multiply(bBaseWeiNum).toString());

        result = new BigInteger(result.add(yvWei).toString());
        return result.toString();
    }
}
