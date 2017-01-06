package com.javarush.test.level04.lesson10.task01;

/* 10 чисел
Вывести на экран числа от 1 до 10 используя цикл while.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int a = 1;
        while (a < 11)
        {
            System.out.print(a + " ");
            a++;
        }

    }
}