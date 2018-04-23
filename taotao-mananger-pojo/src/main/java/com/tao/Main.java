package com.tao;

import java.util.Scanner;

/**
 * Created by 28029 on 2018/4/5.
 */
public class Main {
    private static long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aNum = 0;
        int bNum = 0;
        int aLength = 0;
        int bLength = 0;
        int k = 0;
        while(sc.hasNext())
        {
            k = sc.nextInt();
            aLength = sc.nextInt();
            aNum = sc.nextInt();

            bLength = sc.nextInt();
            bNum = sc.nextInt();
            System.out.println(Main.getCountAfterMod(k,aLength,aNum,bLength,bNum));
        }
    }

    public static  long getCountAfterMod(int k, int aLength,int aNum, int bLength, int bNum)
    {
        long counts = 0;
        for(int i = aNum; i>=0; i--)
        {
            int countSelectALength = i*aLength;
            if(countSelectALength > k)
                continue;
            else{
                int shenyunLength = k - countSelectALength;
                if(shenyunLength%bLength == 0)
                {
                    int selectBNum = shenyunLength/bNum;
                    counts+=getCountHasSelectAB(aNum,i,bNum,selectBNum);
                    counts = counts%MOD;

                }
            }
        }

        return counts;
    }

    private static long getCountHasSelectAB(int aNum, int aSelectNum, int bNum, int bSelectNum)
    {

        System.out.println("aN:"+aNum+" AS:"+aSelectNum+" bN:"+bNum+"  bS:"+bSelectNum);
        long acount = 1;
        if(aSelectNum >0)
        {
            acount = aNum;
            for(int i = 1;i < aSelectNum; i++)
            {
                acount*=(aNum-i);
            }

            long fengmu = 1;
            for(int i = 1; i <= aSelectNum; i++)
                fengmu *= i;

            acount = acount/fengmu;
        }


        long bcount = 1;
        if(bSelectNum > 0)
        {
            bcount = bNum;
            for(int i = 1; i < bSelectNum; i++)
            {
                bcount *= (bNum-i);
            }

            long fengmu = 1;
            for(int i = 1; i <= bSelectNum; i++)
                fengmu *=i;
            bcount /= fengmu;
        }
        System.out.println(bcount*acount);
        return bcount*acount;
    }

}
