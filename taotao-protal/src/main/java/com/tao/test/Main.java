package com.tao.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 28029 on 2018/4/9.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 1; i <= t;i++ )
        {
            String str = sc.nextLine();
            if(str == null || str.equals(""))
            {
                i--;
            }
            else{
                String result = Main.canSuit2(str);
                if(result != null)
                    System.out.println(result);
            }

       }
//        String str =")))(((";
//       String re = Main.canSuit(str);
//        System.out.println("suit_1:"+re);
//        re = Main.canSuit2(str);
//        System.out.println("suit_2:"+re);

    }

    public static String canSuit(String str)
    {
        if(str == null)
            return "No";
        if(str.equals(""))
            return null;

        Stack<Character> stack = new Stack<>();

        boolean isMoveRight = false;
        boolean isChangeOnce = false;
        for(int i = 0;i<str.length();i++)
        {

            if(str.charAt(i)==')')
            {
                if(!stack.empty())
                    stack.pop();
                else
                {
                    if(isMoveRight)
                        return "No";
                    else
                    {
                        isMoveRight=true;
                    }
                }
            }else if(str.charAt(i)=='(')
            {
                if(!isMoveRight)
                    stack.push('(');
                else
                {
                    if(isChangeOnce)
                        stack.push('(');
                    else
                        isChangeOnce=true;
                }
            }
        }

        if(stack.empty())
        {
            if(isMoveRight)
                if(isChangeOnce)
                     return "Yes";
                else
                    return "No";
            else
                return "Yes";
        }

        else if(stack.size() == 1 && isChangeOnce == true)
            return "Yes";

        return "No";
    }


    public static String canSuit2(String str)
    {
        if(str == null)
            return "No";
        if(str.equals(""))
            return null;

        Stack<Character> stack = new Stack<>();

        boolean isMoveRight = false;
        boolean isChangeOnce = false;
        for(int i = 0;i<str.length();i++)
        {

            if(str.charAt(i)==')')
            {
                if(!stack.empty())
                    stack.pop();
                else
                {
                    if(isMoveRight)
                        return "No";
                    else
                    {
                        isMoveRight=true;
                    }
                }
            }else
            {
                stack.push('(');

            }
        }

        if(stack.empty())
        {
            return "Yes";
        }

        else if(stack.size() == 1 && isMoveRight)
            return "Yes";

        return "No";
    }
}
