package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergey on 22.01.2017.
 */
public class DirectorTablet
{
    private DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit()
    {
        Map<Date, Long> advertisingData = StatisticEventManager.getInstance().advertisingData();

        double totalAmount = 0d;
        for (Map.Entry<Date, Long> entry : advertisingData.entrySet())
        {
            double amount = (double) entry.getValue() / 100;
            totalAmount += amount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", dateFormat.format(entry.getKey()), amount));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));
    }

    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> cookedData = StatisticEventManager.getInstance().cookedData();

        for (Map.Entry<Date, Map<String, Integer>> entry : cookedData.entrySet())
        {
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(entry1.getKey() + " - " + entry1.getValue() + " min");
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet()
    {
        Set<Advertisement> listActiveVideo = StatisticAdvertisementManager.getInstance().getData("ActiveVideo");

        for (Advertisement ad : listActiveVideo)
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
    }

    public void printArchivedVideoSet()
    {
        Set<Advertisement> listNoActiveVideo = StatisticAdvertisementManager.getInstance().getData("NoActiveVideo");

        for (Advertisement ad : listNoActiveVideo)
            ConsoleHelper.writeMessage(ad.getName());
    }
}
