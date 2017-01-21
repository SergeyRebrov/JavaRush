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
        List<Advertisement> set = new ArrayList<>();
        List<Advertisement> videos = recursion(storage.list(), set, 0, storage.list().size() - 1);

        if (videos.isEmpty())
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

    public List<Advertisement> recursion(List<Advertisement> list, List<Advertisement> set, int listPosition, int limit)
    {
        if (listPosition > limit)
            return set;

        List<Advertisement> oneList = recursion(list, set, listPosition + 1, limit);

        int duration = 0;

        List<Advertisement> newSet = new ArrayList<>(set);
        if (!newSet.isEmpty())
            for (Advertisement ad : newSet)
                duration += ad.getDuration();

        Advertisement advertisement = list.get(listPosition);

        if (duration + advertisement.getDuration() <= timeSeconds && !newSet.contains(advertisement) && advertisement.getHits() > 0)
            newSet.add(advertisement);

        List<Advertisement> twoList = recursion(list, newSet, listPosition + 1, limit);


        int oneMaxAmount = 0;
        int oneMaxDuration = 0;
        int oneMaxVideos = oneList.size();

        for (Advertisement ad : oneList)
        {
            oneMaxAmount += ad.getAmountPerOneDisplaying();
            oneMaxDuration += ad.getDuration();
        }

        int twoMaxAmount = 0;
        int twoMaxDuration = 0;
        int twoMaxVideos = twoList.size();

        for (Advertisement ad : twoList)
        {
            twoMaxAmount += ad.getAmountPerOneDisplaying();
            twoMaxDuration += ad.getDuration();
        }

        if (oneMaxAmount > twoMaxAmount)
            return oneList;
        else if (oneMaxAmount < twoMaxAmount)
            return twoList;
        else if (oneMaxDuration > twoMaxDuration)
            return oneList;
        else if (oneMaxDuration < twoMaxDuration)
            return twoList;
        else if (oneMaxVideos > twoMaxVideos)
            return twoList;
        else
            return oneList;
    }
}
