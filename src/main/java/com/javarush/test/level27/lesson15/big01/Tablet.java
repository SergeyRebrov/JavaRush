package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Tablet
{
    private final int number;

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
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
            try
            {
                queue.put(order);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void createTestOrder() {
        try
        {
            Order order = new TestOrder(this);
            workOrder(order);
        }

        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
