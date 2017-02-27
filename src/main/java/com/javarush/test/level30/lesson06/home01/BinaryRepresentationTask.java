package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Sergey on 14.02.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private int i;

    public BinaryRepresentationTask(int i)
    {
        this.i = i;
    }

    @Override
    protected String compute()
    {
        int a = i % 2;
        int b = i / 2;

        BinaryRepresentationTask binaryOne = new BinaryRepresentationTask(a);
        binaryOne.fork();
        String result = String.valueOf(a);

        if (b > 0)
        {
            BinaryRepresentationTask binaryTwo = new BinaryRepresentationTask(b);
            binaryTwo.fork();
            return binaryTwo.join() + result;
        }
        else
            return result;
    }
}
