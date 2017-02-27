package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInput = new FileInputStream(reader.readLine());
        FileOutputStream fileOutput = new FileOutputStream(reader.readLine());
        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());

        if (fileInput.available() > 0 && fileInput.available() % 2 == 0)
        {
            byte[] buffer = new byte[fileInput.available() / 2];
            int count = fileInput.read(buffer);
            fileOutput.write(buffer, 0, count);
            count = fileInput.read(buffer);
            fileOutputStream.write(buffer, 0, count);
        }

        if (fileInput.available() > 0 && fileInput.available() % 2 != 0)
        {
            byte[] buffer = new byte[fileInput.available() / 2 + 1];
            int count = fileInput.read(buffer);
            fileOutput.write(buffer, 0, count);
            count = fileInput.read(buffer);
            fileOutputStream.write(buffer, 0, count);
        }

        reader.close();
        fileInput.close();
        fileOutput.close();
        fileOutputStream.close();
    }
}
