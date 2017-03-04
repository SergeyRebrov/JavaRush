package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 04.03.2017.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        long start = new Date().getTime();
        for (String string : strings)
            ids.add(shortener.getId(string));
        long end = new Date().getTime();

        return end - start;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        long start = new Date().getTime();
        for (long id : ids)
            strings.add(shortener.getString(id));
        long end = new Date().getTime();

        return end - start;
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        Set<Long> longs1 = new HashSet<>();
        Set<Long> longs2 = new HashSet<>();
        long testTime1 = getTimeForGettingIds(shortener1, origStrings, longs1);
        long testTime2 = getTimeForGettingIds(shortener2, origStrings, longs2);

        Assert.assertTrue(testTime1 > testTime2);

        testTime1 = getTimeForGettingStrings(shortener1, longs1, new HashSet<String>());
        testTime2 = getTimeForGettingStrings(shortener2, longs2, new HashSet<String>());

        Assert.assertEquals(testTime1, testTime2, 5);
    }
}
