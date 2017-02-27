package com.javarush.test.level27.lesson15.big01;

import java.util.List;

/**
 * Created by Sergey on 24.01.2017.
 */
public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tabletList;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tabletList = tablets;
        this.interval = interval;
    }

    @Override
    public void run()
    {
        if (tabletList.isEmpty()) return;


        try
        {
            while (true)
            {
                Tablet randomTablet = tabletList.get((int) (Math.random() * tabletList.size()));
                randomTablet.createTestOrder();
                Thread.sleep(interval);
            }
        }
        catch (InterruptedException ignore)
        {}
    }
}
