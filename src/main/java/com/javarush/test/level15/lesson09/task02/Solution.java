package com.javarush.test.level15.lesson09.task02;

/* Статики 2
1. В статическом блоке считайте две переменные с консоли А и В с типом int.
2. Не забыть про IOException, который надо обработать в блоке catch.
3. Закрыть поток ввода методом close().
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static int A;
    public static int B;

    static
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            A = Integer.parseInt(reader.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            B = Integer.parseInt(reader.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static final int MIN = min(A, B);

    public static void main(String[] args)
    {
        System.out.println(MIN);
    }

    public static int min(int a, int b)
    {
        return a < b ? a : b;
    }
}
