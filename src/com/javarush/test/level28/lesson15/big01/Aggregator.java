package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;

/**
 * Created by Sergey on 26.01.2017.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        Provider provider = new Provider(new HHStrategy()
        {
        });
        Controller controller = new Controller(provider);

        controller.scan();
    }
}
