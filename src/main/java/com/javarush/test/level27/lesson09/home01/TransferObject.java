package com.javarush.test.level27.lesson09.home01;

public class TransferObject
{
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get()
    {

        try
        {
            while (!isValuePresent)
                this.wait();

            System.out.println("Got: " + value);
            isValuePresent = false;
            this.notifyAll();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return value;
    }

    public synchronized void put(int value)
    {
        try
        {

            while (isValuePresent)
                this.wait();

            this.value = value;
            System.out.println("Put: " + value);
            isValuePresent = true;
            this.notifyAll();

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
