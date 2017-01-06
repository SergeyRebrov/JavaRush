package com.javarush.test.level04.lesson13.task03;

/* Рисуем треугольник
Используя цикл for вывести на экран прямоугольный треугольник из восьмёрок со сторонами 10 и 10.
Пример:
8
88
888
...
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        for (int a = 1; a < 11; a++)
        {
            for (int b = 0; b < a; b++)
            {
                System.out.print("8");
            }
            System.out.println();

        }

    }
}
