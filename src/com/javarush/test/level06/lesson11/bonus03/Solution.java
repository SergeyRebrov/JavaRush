package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int d = Integer.parseInt(reader.readLine());
        int e = Integer.parseInt(reader.readLine());

        ArrayList<Integer> x = new ArrayList<Integer>();
        x.add(a);
        x.add(b);
        x.add(c);
        x.add(d);
        x.add(e);

        int m = getMin(a, b);
        int n = getMin(m, c);
        int o = getMin(n, d);
        int min = getMin(o, e);

        m = getMax(a, b);
        n = getMax(m, c);
        o = getMax(n, d);
        int max = getMax(o, e);


        for (int i = min; i <= max; i++)
        {
            for (int number : x)
            {
                if (i == number)
                    System.out.println(number);
            }
        }

    }

    public static int getMin(int a, int b)
    {
        return a < b ? a : b;
    }

    public static int getMax(int a, int b)
    {
        return a > b ? a : b;
    }
}
