package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Sergey on 14.02.2017.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            for (int i = 1; i <= 9; i++)
            {
                ShareItem shareItem = new ShareItem("ShareItem-" + i, i);
                System.out.format("Элемент '%s' добавлен", shareItem.getDescription());
                queue.offer(shareItem);
                System.out.println();

                Thread.sleep(100);

                if (queue.hasWaitingConsumer())
                    System.out.println("Consumer в ожидании!");
            }
        }
        catch (InterruptedException ignore)
        {
        }
    }
}
