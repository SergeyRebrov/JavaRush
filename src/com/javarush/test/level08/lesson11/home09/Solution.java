package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 3 2000"));
    }

    public static boolean isDateOdd(String date)
    {


        Date yearStartTime = new Date(date);
        yearStartTime.setHours(0);
        yearStartTime.setMinutes(0);
        yearStartTime.setSeconds(0);
        yearStartTime.setDate(1);
        yearStartTime.setMonth(0);

        Date currentTime = new Date(date);

        long msTime = currentTime.getTime() - yearStartTime.getTime();
        System.out.println(msTime);
        long msDay = 24 * 60 * 60 * 1000;
        System.out.println(msDay);

        int dayCount = (int) (msTime / msDay);
        System.out.println(dayCount);
        if (dayCount % 2 == 0)
            return true;
        else
            return false;
    }
}
