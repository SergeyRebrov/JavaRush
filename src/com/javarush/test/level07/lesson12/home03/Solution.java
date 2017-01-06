package com.javarush.test.level07.lesson12.home03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Максимальное и минимальное числа в массиве
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Найти максимальное и минимальное числа в массиве.
Вывести на экран максимальное и минимальное числа через пробел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;

        int[] list = new int[20];
        for (int i = 0; i < list.length; i++)
        {
            list[i] = Integer.parseInt(reader.readLine());
            if (list[i] > maximum)
                maximum = list[i];
            if (list[i] < minimum)
                minimum = list[i];
        }


        System.out.println(maximum);
        System.out.println(minimum);
    }
}
