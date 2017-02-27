package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        ArrayList<String>[] createL = new ArrayList[3];

        for (int j = 0; j < createL.length; j++)
        {
            ArrayList<String> list = new ArrayList<String>();
            String s = "";
            for (int i = 0; i < 10; i++)
            {
                s += i;
                list.add(s);
            }
            createL[j] = list;
        }
        return createL;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list : arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}