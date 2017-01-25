package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sergey on 25.01.2017.
 */
public class MyThread extends Thread
{
    private static AtomicInteger numberPriority = new AtomicInteger(0);

    public MyThread()
    {
        setNumberPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        setNumberPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        setNumberPriority();
    }

    public MyThread(String name)
    {
        super(name);
        setNumberPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setNumberPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setNumberPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setNumberPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        setNumberPriority();
    }

    private void setNumberPriority()
    {
        if (numberPriority.get() == 10)
            numberPriority.set(0);

        setPriority(numberPriority.incrementAndGet());
    }

}
