package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Order
{
    private List<Dish> dishes;

    private Tablet tablet;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString()
    {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of Tablet{number=" + tablet.getNumber() + "}";
    }
}
