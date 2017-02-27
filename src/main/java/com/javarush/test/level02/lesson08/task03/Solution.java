package com.javarush.test.level02.lesson08.task03;

/* Минимум трех чисел
Написать функцию, которая вычисляет минимум из трёх чисел.
Подсказка:
Нужно написать тело существующей функции min и исправить возвращаемое значение.
*/
public class Solution
{
    public static int min(int a, int b, int c)
    {
        if (a < b)
        {
            if (a < c)
            {
                return a;
            } else
            {
                return c;
            }
        } else
        {
            if (b < c)
            {
                return b;
            } else
            {
                return c;
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(min(1, 2, 3));
        System.out.println(min(-1, -2, -3));
        System.out.println(min(3, 5, 3));
        System.out.println(min(10, 5, 10));
    }

}