package com.javarush.test.level08.lesson11.home04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Минимальное из N чисел
1. Ввести с клавиатуры число N.
2. Считать N целых чисел и заполнить ими список - метод getIntegerList.
3. Найти минимальное число среди элементов списка - метод getMinimum.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        List<Integer> integerList = getIntegerList();
        System.out.println(getMinimum(integerList));
    }

    public static int getMinimum(List<Integer> array)
    {
        int a = array.get(0);
        for (int i : array)
        {
            if (a > i)
                a = i;
        }
        return a;
    }

    public static List<Integer> getIntegerList() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
        {
            int a = Integer.parseInt(reader.readLine());
            list.add(a);
        }
        return (List<Integer>) list;
    }
}
