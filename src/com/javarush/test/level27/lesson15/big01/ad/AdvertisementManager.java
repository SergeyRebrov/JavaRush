package com.javarush.test.level27.lesson15.big01.ad;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergey on 18.01.2017.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException
    {
        List<Advertisement> videos = new ArrayList<>();

        int getSeconds = timeSeconds;

        for (Advertisement advertisement : storage.list())
        {
            if (advertisement.getDuration() <= getSeconds)
            {
                videos.add(advertisement);
                getSeconds -= advertisement.getDuration();
            }
        }

        if (videos.isEmpty() || getSeconds == timeSeconds)
            throw new NoVideoAvailableException();

        Collections.sort(videos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {

                int result = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (result == 0)
                {
                    result = Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration(), o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                    return result;
                } else
                    return result;
            }
        });

        for (Advertisement advertisement : videos)
        {
            ConsoleHelper.writeMessage(advertisement.getName() +
                    " is displaying... " + advertisement.getAmountPerOneDisplaying() +
                    ", " + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }
}
