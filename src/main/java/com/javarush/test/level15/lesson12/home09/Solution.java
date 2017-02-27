package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        ArrayList<String> param = new ArrayList<String>();
        ArrayList<String> value = new ArrayList<String>();
        String p = "";
        String v = "";
        boolean pv = true;
        char[] buffer = line.toCharArray();
        for (char symbol : buffer)
        {
            if (symbol == '?')
            {
                p = "";
                v = "";
                continue;
            }
            if (symbol == '=')
            {
                pv = false;
                continue;
            }
            if (symbol == '&')
            {
                param.add(p);
                value.add(v);
                p = "";
                v = "";
                pv = true;
                continue;
            }
            if (pv)
            {
                p += symbol;
            } else
            {
                v += symbol;
            }
        }
        param.add(p);
        value.add(v);
        reader.close();

        for (String s : param)
            System.out.print(s + " ");
        System.out.println();
        for (int i = 0; i < param.size(); i++)
        {
            if (param.get(i).equals("obj"))
            {
                try
                {
                    alert(Double.parseDouble(value.get(i)));
                }
                catch (NumberFormatException e)
                {
                    alert(value.get(i));
                }
            }
        }

    }

    public static void alert(double value)
    {
        System.out.println("double " + value);
    }

    public static void alert(String value)
    {
        System.out.println("String " + value);
    }
}
