package com.javarush.test.level30.lesson15.big01.client;


import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sergey on 15.02.2017.
 */
public class BotClient extends Client
{
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        return "date_bot_" + count.incrementAndGet();
    }

    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread
    {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);

            if (message != null && !message.isEmpty() && message.contains(": "))
            {
                String[] strings = message.split(": ");
                DateFormat dateFormat;
                String info = "Информация для " + strings[0] + ": ";
                switch (strings[1])
                {
                    case ("дата"):
                        dateFormat = new SimpleDateFormat("d.MM.YYYY");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("день"):
                        dateFormat = new SimpleDateFormat("d");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("месяц"):
                        dateFormat = new SimpleDateFormat("MMMM");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("год"):
                        dateFormat = new SimpleDateFormat("YYYY");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("время"):
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("час"):
                        dateFormat = new SimpleDateFormat("H");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("минуты"):
                        dateFormat = new SimpleDateFormat("m");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    case ("секунды"):
                        dateFormat = new SimpleDateFormat("s");
                        sendTextMessage(info + dateFormat.format(new GregorianCalendar().getTime()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
