package com.javarush.test.level26.lesson10.home01;


import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergey on 02.01.2017.
 */
public class Consumer implements Runnable
{
    protected BlockingQueue queue;

    public Consumer(BlockingQueue queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                System.out.println(queue.take());
            }
        }
        catch (
                InterruptedException e)

        {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
