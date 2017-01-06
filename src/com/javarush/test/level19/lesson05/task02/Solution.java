package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        int count = 0;
        String s = "";
        while (fileReader.ready())
        {
            char c = (char) fileReader.read();
            if (Character.toString(c).matches("[a-z]"))
            {
                s += c;
            } else
            {
                if (s.equals("world"))
                    count++;
                s = "";
            }
        }
        reader.close();
        fileReader.close();
        System.out.println(count);
    }
}
