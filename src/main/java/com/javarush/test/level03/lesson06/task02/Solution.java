package com.javarush.test.level03.lesson06.task02;

/* Таблица умножения
Выведи на экран таблицу умножения 10 на 10 в следующем виде:
1 2 3 …
2 4 6 …
3 6 9 …
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        for (int a = 1; a < 11; a++)
        {
            for (int b = 1; b < 11; b++)
            {
                System.out.print(a * b + " ");
            }
            System.out.println();
        }

    }
}