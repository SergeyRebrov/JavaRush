package com.javarush.test.level27.lesson15.big01.ad;

import java.util.*;

/**
 * Created by Sergey on 23.01.2017.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private StatisticAdvertisementManager() {}

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance()
    {
        return instance;
    }

    public Set<Advertisement> getData(String param) {
        Set<Advertisement> data = new TreeSet<Advertisement>(new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        if (param.equals("ActiveVideo"))
            for (Advertisement ad : storage.list())
                if (ad.getHits() > 0)
                    data.add(ad);

        if (param.equals("NoActiveVideo"))
            for (Advertisement ad : storage.list())
                if (ad.getHits() == 0)
                    data.add(ad);

        return data;
    }
}
