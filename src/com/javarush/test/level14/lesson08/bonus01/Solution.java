package com.javarush.test.level14.lesson08.bonus01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //2
        try
        {
            Object o = null;
            o.toString();
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //3
        try
        {
            File.createTempFile("prefix", "", new File("D:/prologistic/tmp"));
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //4
        try
        {
            FileInputStream fileInputStream = new FileInputStream("D:/123456789.txt");
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //5
        try
        {
            Double.parseDouble("Привет!");
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //6
        try
        {
            int[] i = new int[3];
            i[0] = 1;
            i[1] = 2;
            i[2] = 3;
            i[3] = 4;
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //7
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("12-MM-yyyy");
            Date date = simpleDateFormat.parse("01-01-2016");
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //8
        try
        {
            Object o = new Solution();
            String s = (String) o;
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //9
        try
        {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            for (int i = 0; i < 6; i++)
                list.get(i);
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        //10
        try
        {
            String s = "Hello world!";
            char c = s.charAt(15);
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

    }
}
