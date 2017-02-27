package com.javarush.test.level27.lesson15.big01.kitchen;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Cook extends Observable implements Runnable
{
    private String name;

    private boolean busy;

    private LinkedBlockingQueue<Order> queue;

    public Cook(String name)
    {
        this.name = name;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);

        try
        {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        busy = false;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                if (!queue.isEmpty())
                    if (!isBusy())
                        startCookingOrder(queue.take());

                Thread.sleep(10);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
