package com.javarush.test.level03.lesson05.task03;

/* Конвертер времени
Добавьте метод public static int convertToSeconds(int hour) который будет конвертировать часы в секунды.
Вызовите его дважды в методе main с любыми параметрами. Результаты выведите на экран, каждый раз с новой строки.
*/

public class Solution
{

    public static int convertToSeconds(int hour)
    {
        int sec = hour * 3600;
        return sec;
    }

    public static void main(String[] args)
    {
        System.out.println(convertToSeconds(4));
        System.out.println(convertToSeconds(12));
    }
}