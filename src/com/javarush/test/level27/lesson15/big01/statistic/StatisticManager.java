package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;

import java.util.*;

/**
 * Created by Sergey on 21.01.2017.
 */
public class StatisticManager
{
    private static StatisticManager instance;
    private static StatisticStorage statisticStorage = getInstance().new StatisticStorage();

    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager(){}

    public static StatisticManager getInstance()
    {
        if (instance == null)
            instance = new StatisticManager();
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public List<EventDataRow> advertisingData ()
    {
        return statisticStorage.advertisingData();
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> eventTypeMap = new HashMap<>();
        private StatisticStorage () {
            for (EventType value : EventType.values())
                eventTypeMap.put(value, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) {
            eventTypeMap.get(data.getType()).add(data);
        }

        private List<EventDataRow> advertisingData ()
        {
            return eventTypeMap.get(EventType.SELECTED_VIDEOS);
        }
    }
}
