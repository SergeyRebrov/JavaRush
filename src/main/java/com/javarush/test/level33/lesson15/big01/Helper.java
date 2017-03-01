package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Sergey on 01.03.2017.
 */
public class Helper
{
    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString()
    {
        return new BigInteger(130, random).toString(32);
    }

    public static void printMessage(String message)
    {
        System.out.println(message);
    }
}
