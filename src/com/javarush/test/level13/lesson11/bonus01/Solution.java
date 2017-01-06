package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        InputStream inStream = new FileInputStream(fileName);

        String number = "";
        boolean n = false;
        ArrayList<Integer> list = new ArrayList<Integer>();

        while (inStream.available() > 0)
        {
            int data = inStream.read();
            char c = (char) data;
            String s = Character.toString(c);
            try
            {
                int a = Integer.parseInt(s);
                number += s;
                n = true;

            }
            catch (NumberFormatException e)
            {
                if (n)
                {

                    list.add(Integer.parseInt(number));
                    number = "";
                    n = false;
                }
                if (s.equals("-"))
                    number += s;
            }
        }
        try
        {
            list.add(Integer.parseInt(number));
        }
        catch (NumberFormatException e)
        {
        }

        Collections.sort(list);
        for (int i : list)
        {
            if (i % 2 == 0)
                System.out.println(i);
        }

    }
}
