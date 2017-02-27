package com.javarush.test.level04.lesson13.task01;

/* Четные числа
Используя цикл for вывести на экран чётные числа от 1 до 100 включительно.
Через пробел либо с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        for (int a = 1; a < 101; a++)
        {
            if (a % 2 == 0)
                System.out.print(a + " ");
        }

    }
}
