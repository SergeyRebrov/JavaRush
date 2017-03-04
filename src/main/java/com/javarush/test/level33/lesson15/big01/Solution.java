package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.FileStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 01.03.2017.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> longSet = new HashSet<>();

        for (String string : strings)
            longSet.add(shortener.getId(string));

        return longSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> longSet = new HashSet<>();

        for (Long id : keys)
            longSet.add(shortener.getString(id));

        return longSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> stringsSetTest = new HashSet<>();

        for (long i = 0; i < elementsNumber; i++)
        {
            stringsSetTest.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        long start = new Date().getTime();
        Set<Long> ids = getIds(shortener, stringsSetTest);
        long end = new Date().getTime();
        System.out.println(end - start);

        start = new Date().getTime();
        Set<String> strings = getStrings(shortener, ids);
        end = new Date().getTime();
        System.out.println(end - start);

        if (stringsSetTest.containsAll(strings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args)
    {
        testStrategy(new FileStorageStrategy(), 100);
    }
}
