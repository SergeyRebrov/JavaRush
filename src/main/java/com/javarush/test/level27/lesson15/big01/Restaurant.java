package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by Sergey on 18.01.2017.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException
    {
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(QUEUE);
        Cook cook2 = new Cook("Elli");
        cook2.setQueue(QUEUE);

        Waitor waitor = new Waitor();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);

        Thread threadCook1 = new Thread(cook1);
        Thread threadCook2 = new Thread(cook2);
        threadCook1.start();
        threadCook2.start();

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            tabletList.add(new Tablet(i));
            tabletList.get(i).setQueue(QUEUE);
        }
        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generatorTask);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

