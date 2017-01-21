package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;

/**
 * Created by Sergey on 21.01.2017.
 */
public class StatisticManager
{
    private static StatisticManager instance = new StatisticManager();

    private StatisticManager(){}

    public static StatisticManager getInstance()
    {
        return instance;
    }

    public void register(EventDataRow data) {
        //TODO
    }
}
