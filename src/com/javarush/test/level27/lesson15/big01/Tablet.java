package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Tablet extends Observable
{
    private final int number;

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder(){
        try
        {
            Order order = new Order(this);
            workOrder(order);
        }

        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void workOrder(Order order)
    {
        if (!order.isEmpty())
        {
            ConsoleHelper.writeMessage(order.toString());
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            try
            {
                advertisementManager.processVideos();
            }
            catch (NoVideoAvailableException e)
            {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
            setChanged();
            notifyObservers(order);

        }
    }

    public void createTestOrder() {
        try
        {
            TestOrder order = new TestOrder(this);
            workOrder(order);
        }

        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
