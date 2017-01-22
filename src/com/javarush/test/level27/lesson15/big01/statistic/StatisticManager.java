package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergey on 21.01.2017.
 */
public class StatisticManager
{
    private static StatisticManager instance;
    private static StatisticStorage statisticStorage = getInstance().new StatisticStorage();

    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager()
    {
    }

    public static StatisticManager getInstance()
    {
        if (instance == null)
            instance = new StatisticManager();
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

    public Map<String, Long> advertisingData()
    {
        Map<String, Long> advertisingData = new LinkedHashMap<>();

        List<EventDataRow> eventDataRows = statisticStorage.advertisingData();
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
            VideoSelectedEventDataRow videoData = (VideoSelectedEventDataRow) eventData;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy", Locale.ENGLISH);
            if (advertisingData.containsKey(dateFormat.format(videoData.getDate())))
                advertisingData.put(dateFormat.format(videoData.getDate()),
                        advertisingData.get(dateFormat.format(videoData.getDate())) + videoData.getAmount());
            else
                advertisingData.put(dateFormat.format(videoData.getDate()), videoData.getAmount());
        }
        return advertisingData;
    }

    public Map<String, Map<String, Integer>> cookedData()
    {
        Map<String, Map<String, Integer>> cookedData = new LinkedHashMap<>();

        List<EventDataRow> eventDataRows = statisticStorage.cookedData();
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
            CookedOrderEventDataRow cooked = (CookedOrderEventDataRow) eventData;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy", Locale.ENGLISH);
            Map<String, Integer> cook_time = new TreeMap<>();
            if (cookedData.containsKey(dateFormat.format(cooked.getDate())))
            {
                cook_time = cookedData.get(dateFormat.format(cooked.getDate()));

                if (cook_time.containsKey(cooked.getCookName()))
                {
                    cook_time.put(cooked.getCookName(), cook_time.get(cooked.getCookName()) + cooked.getTime());
                } else
                {
                    cook_time.put(cooked.getCookName(), cooked.getTime());
                }

                cookedData.put(dateFormat.format(cooked.getDate()), cook_time);
            } else
            {
                cook_time.put(cooked.getCookName(), cooked.getTime());
                cookedData.put(dateFormat.format(cooked.getDate()), cook_time);
            }
        }
        return cookedData;
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

        private List<EventDataRow> advertisingData()
        {
            return eventTypeMap.get(EventType.SELECTED_VIDEOS);
        }

        private List<EventDataRow> cookedData()
        {
            return eventTypeMap.get(EventType.COOKED_ORDER);
        }
    }
}
