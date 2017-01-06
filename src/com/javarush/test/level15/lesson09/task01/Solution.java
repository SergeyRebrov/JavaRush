package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution
{
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static
    {
        labels.put(12.12, "Go");
        labels.put(13.13, "Hello");
        labels.put(14.14, "Cat");
        labels.put(15.15, "Dog");
        labels.put(16.16, "Bird");
    }

    public static void main(String[] args)
    {
        System.out.println(labels);
    }
}
