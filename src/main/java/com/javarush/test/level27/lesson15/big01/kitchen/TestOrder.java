package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergey on 24.01.2017.
 */
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException
    {
        Dish[] dishList = Dish.values();
        int randomNumber = 1 + (int) (Math.random() * 5);
        dishes = new ArrayList<>();
        while (randomNumber > 0)
        {
            int randomDish = (int) (Math.random() * dishList.length);
            dishes.add(dishList[randomDish]);
            randomNumber--;
        }
    }
}
