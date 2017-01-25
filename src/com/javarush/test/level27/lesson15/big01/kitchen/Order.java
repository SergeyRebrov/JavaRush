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
    protected List<Dish> dishes;

    private Tablet tablet;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString()
    {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of Tablet{number=" + tablet.getNumber() + "}";
    }

    public int getTotalCookingTime() {

        int time = 0;
        for (Dish dish : dishes)
            time += dish.getDuration();

        return time;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public Tablet getTablet()
    {
        return tablet;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    protected void initDishes() throws IOException
    {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
