package com.tao.meituanTest;


import java.util.*;

/**
 * Created by 28029 on 2018/4/20.
 */
public class Main {
    public static void main(String[] args) {

        int N =10;
        int n =2;
        int m = 2;
        int p =10;
        Scanner sc = new Scanner(System.in);

        N= sc.nextInt();
        n  = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        int[] sArray = Main.getSourceArray(N,p);

        int result  = 0;
        List<Integer> addList = Main.getAddArray(n,m);
        for(int i = 0; i < addList.size();i++)
        {
            result += sArray[addList.get(i)];
        }
        System.out.println(result);


    }

    public static int getGcd(int i, int j) {
        int k;
        while ((k=i%j) != 0) {
            i = j;
            j = k;
        }
        return j;
    }

    public static int[] getSourceArray(int n, int p){
        int[] sourceArray  = new int[n+1];
        sourceArray[1] = p;
        for(int i = 2; i<=n ;i++)
        {
            sourceArray[i]=(sourceArray[i-1]+153)%p;
        }
        return sourceArray;
    }

    public static List<Integer> getAddArray2(int n, int m)
    {
        List<Integer> result = new ArrayList<>();

        for(int i = 1; i <=n; i++)
        {
            for(int j =1; j<=m;j++)
            {
                int gcd = Main.getGcd(i,j);
                result.add(gcd);

            }
        }
        return result;
    }
    public static List<Integer> getAddArray(int n, int m)
    {
        int min = n>m?m:n;
        List<Integer> result = new ArrayList<>();
        int[][] gcdMatrix = new int[min][min];

        for(int i = 1; i <=n; i++)
        {
            for(int j =1; j<=m;j++)
            {
                if(i >= min || j  >= min)
                {
                    int gcd = Main.getGcd(i,j);
                    result.add(gcd);
                }
                else
                {
                    if(gcdMatrix[i-1][j-1] < 1)
                    {
                        int gcd = Main.getGcd(i,j);
                        result.add(gcd);

                        gcdMatrix[i-1][j-1]  = gcd;
                        gcdMatrix[j-1][i-1] = gcd;
                    }
                    else
                        result.add(gcdMatrix[i-1][j-1]);
                }

            }
        }
        return result;
    }
}
