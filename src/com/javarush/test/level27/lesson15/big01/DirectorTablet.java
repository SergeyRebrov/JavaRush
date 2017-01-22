package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergey on 22.01.2017.
 */
public class DirectorTablet
{
    public void printAdvertisementProfit() {
        Map<String, Long> advertisingData = new LinkedHashMap<>();

        List<EventDataRow> eventDataRows = StatisticManager.getInstance().advertisingData();
        Collections.sort(eventDataRows, new Comparator<EventDataRow>()
        {
            @Override
            public int compare(EventDataRow o1, EventDataRow o2)
            {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        for (EventDataRow eventData : eventDataRows)
        {
            VideoSelectedEventDataRow videoData = (VideoSelectedEventDataRow)eventData;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy");
            if (advertisingData.containsKey(dateFormat.format(videoData.getDate())))
                advertisingData.put(dateFormat.format(videoData.getDate()),
                        advertisingData.get(dateFormat.format(videoData.getDate())) + videoData.getAmount());
            else
                advertisingData.put(dateFormat.format(videoData.getDate()), videoData.getAmount());
        }

        double totalAmount = 0;
        for (Map.Entry<String, Long> entry : advertisingData.entrySet())
        {
            double amount = (double)entry.getValue() / 100;
            totalAmount += amount;
            System.out.println(entry.getKey() + " - " + amount);
        }
        System.out.println("Total - " + totalAmount);
    }

    public void printCookWorkloading() {}

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
