package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Sergey on 06.08.2016.
 */
public class Sun implements Planet
{
    private static Sun sun;

    private Sun()
    {
    }

    public static Sun getInstance()
    {
        if (sun == null)
            sun = new Sun();

        return sun;
    }
}
