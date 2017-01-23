package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Dish;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by Sergey on 21.01.2017.
 */
public class StatisticManager
{
    private static StatisticManager instance = new StatisticManager();
    private static StatisticStorage statisticStorage = getInstance().new StatisticStorage();

    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager()
    {
    }

    public static StatisticManager getInstance()
    {
        return instance;
    }

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }

    public void register(Cook cook)
    {
        cooks.add(cook);
    }

    public Map<Date, Long> advertisingData()
    {
        Map<Date, Long> advertisingData = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow eventData : statisticStorage.getDate(EventType.SELECTED_VIDEOS))
        {
            VideoSelectedEventDataRow videoData = (VideoSelectedEventDataRow) eventData;

            Date newDate = setDate(videoData.getDate());
            if (advertisingData.containsKey(newDate))
                advertisingData.put(newDate, advertisingData.get(newDate) + videoData.getAmount());
            else
                advertisingData.put(newDate, videoData.getAmount());
        }
        return advertisingData;
    }

    public Map<Date, Map<String, Integer>> cookedData()
    {
        Map<Date, Map<String, Integer>> cookedData = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow eventData : statisticStorage.getDate(EventType.COOKED_ORDER))
        {
            CookedOrderEventDataRow cooked = (CookedOrderEventDataRow) eventData;

            Map<String, Integer> cook_time = new TreeMap<>();

            Date newDate = setDate(cooked.getDate());

            int time = cooked.getTime();
            if (time == 0)
                continue;

            if (time % 60 == 0)
                time = time / 60;
            else
                time = time / 60 + 1;

            if (cookedData.containsKey(newDate))
            {
                cook_time = cookedData.get(newDate);

                if (cook_time.containsKey(cooked.getCookName()))
                {
                    cook_time.put(cooked.getCookName(), cook_time.get(cooked.getCookName()) + time);
                } else
                {
                    cook_time.put(cooked.getCookName(), time);
                }

                cookedData.put(newDate, cook_time);
            } else
            {
                cook_time.put(cooked.getCookName(), time);
                cookedData.put(newDate, cook_time);
            }
        }
        return cookedData;
    }

    public Date setDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> eventTypeMap = new HashMap<>();

        private StatisticStorage()
        {
            for (EventType value : EventType.values())
                eventTypeMap.put(value, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data)
        {
            eventTypeMap.get(data.getType()).add(data);
        }

        private List<EventDataRow> getDate(EventType eventType)
        {
            return eventTypeMap.get(eventType);
        }
    }
}
