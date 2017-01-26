package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.model.Strategy;

/**
 * Created by Sergey on 26.01.2017.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        Provider provider = new Provider(new Strategy()
        {
        });
        Controller controller = new Controller(provider);

        System.out.println(controller);
    }
}
