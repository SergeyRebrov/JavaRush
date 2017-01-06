package com.javarush.test.level15.lesson04.task02;

/* ООП - Перегрузка
Перегрузите метод printMatrix 8 различными способами. В итоге должно получиться 10 различных методов printMatrix.
*/

public class Solution
{
    public static void main(String[] args)
    {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value)
    {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value)
    {
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(value);
            }
            System.out.println();
        }
        printMatrix(m, value, n);
    }

    public static void printMatrix(int m, Object value, int n)
    {
        System.out.println("Выводим максимальное число");
        if (m > n)
            System.out.println(m);
        else
            System.out.println(n);
        printMatrix(value, m, n);
    }

    public static void printMatrix(Object value, int m, int n)
    {
        System.out.println("Выводим минимальное число");
        if (m < n)
            System.out.println(m);
        else
            System.out.println(n);
        String v = (String) value;
        printMatrix(m, v, n);
    }

    public static void printMatrix(int m, String value, int n)
    {
        System.out.println("Выводим строку со всеми данными");
        System.out.println(m + value + n);
        printMatrix(value, m, n);
    }

    public static void printMatrix(String value, int m, int n)
    {
        System.out.println("Преобразуем строку в число");
        int v = Integer.parseInt(value);
        printMatrix(v, m, n);
    }

    public static void printMatrix(int value, int m, int n)
    {
        System.out.println("Преобразуем два числа в строки");
        String v = Integer.toString(value);
        String a = Integer.toString(m);
        printMatrix(v, a, n);
    }

    public static void printMatrix(String value, String m, int n)
    {
        System.out.println("Выводим все на экран");
        System.out.println(value + m + n);
        printMatrix(value, n, m);
    }

    public static void printMatrix(String value, int n, String m)
    {
        System.out.println("Все числа - строки");
        String a = Integer.toString(n);
        printMatrix(value, m, a);
    }

    public static void printMatrix(String value, String m, String n)
    {
        System.out.println("Вот и все!");
    }
}
