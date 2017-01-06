package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Sergey on 05.07.2016.
 */
public class Singleton
{
    private static Singleton singleton = new Singleton();

    private Singleton()
    {
    }

    static Singleton getInstance()
    {
        return singleton;
    }
}
