package com.javarush.test.level08.lesson06.task05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Четыре метода
Реализовать 4 метода. Они должны возвращать список, который лучше всего подходит для выполнения данных операций (быстрее всего справится с большим количеством операций). Ничего измерять не нужно.
*/

public class Solution
{
    public static List getListForGet()
    {
        ArrayList list = new ArrayList();
        return list;
    }

    public static List getListForSet()
    {
        ArrayList list = new ArrayList();
        return list;
    }

    public static List getListForAddOrInsert()
    {
        List list = new LinkedList<>();
        return list;
    }

    public static List getListForRemove()
    {
        List list = new LinkedList<>();
        return list;
    }
}
