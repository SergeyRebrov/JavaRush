package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        Set<String> set = new HashSet<String>();
        set.add("Лом");
        set.add("Ломбард");
        set.add("Лошадь");
        set.add("Любовь");
        set.add("Ласка");
        set.add("Лектор");
        set.add("Лана");
        set.add("Леска");
        set.add("Лоск");
        set.add("Липа");
        set.add("Лента");
        set.add("Ложка");
        set.add("Лор");
        set.add("Ларец");
        set.add("Листок");
        set.add("Листва");
        set.add("Лето");
        set.add("Луна");
        set.add("Лимон");
        set.add("Лютик");

        return (HashSet<String>) set;
    }

    public static void main(String[] args)
    {
        Set<String> set = createSet();

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String s = iterator.next();
            if (s.length() > 4)
            {
                iterator.remove();
            }
        }

        for (String x : set)
        {
            System.out.println(x);
        }
    }

}
