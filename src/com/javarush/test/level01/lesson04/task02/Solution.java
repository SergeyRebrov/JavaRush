package com.javarush.test.level01.lesson04.task02;

/* JavaRush. Learn once - use anywhere
Напиши программу, которая выводит на экран надпись: «JavaRush. Learn once - use anywhere» 10 раз.
*/
public class Solution
{
    public static void main(String[] args)
    {
        String a = "JavaRush. Learn once - use anywhere";
        print10Times(a);
    }


    public static void print10Times(String s)
    {
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
    }
}
