package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        char[] list = s.toCharArray();
        list[0] = Character.toUpperCase(list[0]);
        for (int i = 0; i < list.length; i++)
        {
            if (list[i] == ' ')
                list[i + 1] = Character.toUpperCase(list[i + 1]);
        }
        s = new String(list);
        System.out.println(s);
    }


}
