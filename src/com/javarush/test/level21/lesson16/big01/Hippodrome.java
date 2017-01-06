package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Sergey on 23.10.2016.
 */
public class Hippodrome
{
    private ArrayList<Horse> horses = new ArrayList<Horse>();

    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public static void main(String[] args)
    {
        game = new Hippodrome();
        game.getHorses().add(new Horse("Sivka", 3, 0));
        game.getHorses().add(new Horse("Burka", 3, 0));
        game.getHorses().add(new Horse("Kentavr", 3, 0));

        game.run();
        game.printWinner();
    }

    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            move();
            print();
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void move()
    {
        for (int i = 0; i < getHorses().size(); i++)
        {
            getHorses().get(i).move();
        }
    }

    public void print()
    {
        for (int i = 0; i < getHorses().size(); i++)
        {
            getHorses().get(i).print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner()
    {
        Horse horse = null;
        double maxDistance = 0;

        for (int i = 0; i < getHorses().size(); i++)
        {
            if (getHorses().get(i).getDistance() > maxDistance)
            {
                maxDistance = getHorses().get(i).getDistance();
                horse = getHorses().get(i);
            }
        }
        return horse;
    }

    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

}
