package com.tao.bianyi;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        List<String> list = new LinkedList<>();


        String line = sc.nextLine();
        String[] lineStr = line.split(" ");
        int N = lineStr.length;
        int[][] maxtrix = new int[N][N];
        for(int i = 0; i < N-1; i++)
        {
            maxtrix[0][1] = Integer.parseInt(lineStr[i]);
        }

        int lineIdx = 1;
        while(lineIdx < N)
        {
            for(int i = 0; i <= N-1;i++)
                maxtrix[lineIdx][i] = sc.nextInt();
            lineIdx++;
        }

        int allTimes = N/2;
        for(int timeIdx = 0;timeIdx <= allTimes ; timeIdx++ )
        {
            int[] temp = new int[N-1-timeIdx*2];
            //倒转第一层
            int rowIdx = timeIdx;
            int colStart = N-1-timeIdx;

            int rowEnd = N-1-timeIdx;
            for(int i = temp.length-1 ;i >=0; i--)
            {
                temp[i] = maxtrix[rowIdx+i][colStart];

            }


            for(int i = 1 ;i < temp.length; i++)
            {

            }


        }



    }
}
