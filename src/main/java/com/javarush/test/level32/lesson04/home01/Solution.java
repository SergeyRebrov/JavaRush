package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:/test/1.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        if (is == null)
            return writer;

        InputStreamReader inputStreamReader = new InputStreamReader(is);
        char[] buffer = new char[1 * 1024];
        while (inputStreamReader.ready())
        {
            int length = inputStreamReader.read(buffer);
            writer.write(buffer, 0 , length);
        }
        return writer;
    }
}
