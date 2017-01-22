package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.util.*;

/**
 * Created by Sergey on 22.01.2017.
 */
public class DirectorTablet
{
    public void printAdvertisementProfit() {
        Map<String, Long> advertisingData = StatisticManager.getInstance().advertisingData();

        double totalAmount = 0;
        for (Map.Entry<String, Long> entry : advertisingData.entrySet())
        {
            double amount = (double)entry.getValue() / 100;
            totalAmount += amount;
            System.out.println(entry.getKey() + " - " + amount);
        }
        System.out.println("Total - " + totalAmount);
    }

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> cookedData = StatisticManager.getInstance().cookedData();

        for (Map.Entry<String, Map<String, Integer>> entry : cookedData.entrySet())
        {
            System.out.println(entry.getKey());
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet())
            {
                double time = (double)entry1.getValue() / 60;
                System.out.println(entry1.getKey() + " - " + (int)Math.ceil(time) + " min");
            }
            System.out.println();
        }
    }

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
