package com.javarush.test.level04.lesson16.home02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        if (a > c && a < b)
            System.out.println(a);
        if (a > b && a < c)
            System.out.println(a);
        if (b > c && b < a)
            System.out.println(b);
        if (b > a && b < c)
            System.out.println(b);
        if (c > a && c < b)
            System.out.println(c);
        if (c > b && c < a)
            System.out.println(c);


    }
}
