package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sergey on 18.01.2017.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException
    {
        Cook cook1  = new Cook("Amigo");
        Cook cook2  = new Cook("Elli");

        Waitor waitor = new Waitor();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);


        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            tabletList.add(new Tablet(i));
            if (i % 2 == 0)
                tabletList.get(i).addObserver(cook1);
            else
                tabletList.get(i).addObserver(cook2);

        }
        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generatorTask);
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

