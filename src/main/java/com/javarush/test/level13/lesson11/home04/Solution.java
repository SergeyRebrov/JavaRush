package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        String fileName = reader.readLine();

        while (true)
        {
            String s = reader.readLine();
            list.add(s);
            if (s.equals("exit"))
                break;
            list.add("\r\n");
        }

        FileOutputStream outStream = new FileOutputStream(fileName);
        ArrayList<Character> tmp = new ArrayList<Character>();
        for (String s : list)
        {
            char[] ch = s.toCharArray();
            int data;
            for (int i = 0; i < ch.length; i++)
            {
                data = (int) ch[i];
                outStream.write(data);
            }
        }

        outStream.close();
    }
}
