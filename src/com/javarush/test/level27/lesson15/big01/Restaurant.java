package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Cook;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        Tablet tablet = new Tablet(5);
        tablet.addObserver(new Cook("Amigo"));
        tablet.createOrder();
    }
}
