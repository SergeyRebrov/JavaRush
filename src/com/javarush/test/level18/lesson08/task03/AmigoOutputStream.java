package com.javarush.test.level18.lesson08.task03;

import java.io.*;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream
{
    private FileOutputStream fileOutputStream;

    public static String fileName = "C:/tmp/result.txt";


    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException
    {
        super(fileName);
        this.fileOutputStream = fileOutputStream;
    }

    public void close()
    {
        try
        {
            fileOutputStream.flush();

            String s = "JavaRush © 2012-2013 All rights reserved.";

            fileOutputStream = new FileOutputStream(fileName, true);
            fileOutputStream.write(s.getBytes());

            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        new AmigoOutputStream(new FileOutputStream(fileName)).close();
    }

}
