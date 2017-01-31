package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by Sergey on 31.01.2017.
 */
public class Soldier extends Human
{
    protected boolean isSoldier;

    public Soldier(String name, int age)
    {
        super(name, age);
    }

    public void live() {
            fight();
    }

    public void fight() {
    }

}
