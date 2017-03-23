package com.javarush.test.level38.lesson06.home01;

/**
 * Created by Sergey on 22.03.2017.
 */
public class ExceptionFactory
{
    public static Throwable getFactory(Enum type)
    {
        if (type == null)
            return new IllegalArgumentException();

        String message = type.toString().toLowerCase().replaceAll("_", " ");
        message = message.replaceFirst(String.valueOf(message.charAt(0)), String.valueOf(message.charAt(0)).toUpperCase());
        switch (type.getClass().getSimpleName())
        {
            case ("ExceptionApplicationMessage"):
                return new Exception(message);
            case ("ExceptionUserMessage"):
                return new Error(message);
            case ("ExceptionDBMessage"):
                return new RuntimeException(message);
            default:
                return new IllegalArgumentException();
        }
    }
}
