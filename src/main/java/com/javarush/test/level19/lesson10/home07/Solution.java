package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File(args[0]));

        FileWriter fileWriter = new FileWriter(args[1]);
        boolean read = false;
        while (scanner.hasNext())
        {
            String word = scanner.next();
            if (word.length() > 6)
            {
                if (!read)
                {
                    fileWriter.write(word);
                    read = true;
                }
                else
                {
                    fileWriter.write(',');
                    fileWriter.write(word);
                }
            }
        }

        scanner.close();
        fileWriter.close();
    }
}
