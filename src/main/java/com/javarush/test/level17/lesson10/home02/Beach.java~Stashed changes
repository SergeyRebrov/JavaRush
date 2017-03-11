package com.javarush.test.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

import java.util.ArrayList;

public class Beach implements Comparable<Beach>
{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality)
    {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName()
    {
        return name;
    }

    public synchronized void setName(String name)
    {
        this.name = name;
    }

    public synchronized float getDistance()
    {
        return distance;
    }

    public synchronized void setDistance(float distance)
    {
        this.distance = distance;
    }

    public synchronized int getQuality()
    {
        return quality;
    }

    public synchronized void setQuality(int quality)
    {
        this.quality = quality;
    }

    public static void main(String[] args)
    {
        ArrayList<Beach> list = new ArrayList<Beach>();

        list.add(new Beach("1", 547.0f, 0));
        list.add(new Beach("2", 861.5f, 1));
        list.add(new Beach("3", 993.4f, 3));
        list.add(new Beach("4", 165.4f, 4));
        list.add(new Beach("5", 940.4f, 5));
        list.add(new Beach("6", 451.8f, 6));
        list.add(new Beach("7", 186.4f, 6));
        list.add(new Beach("8", 254.6f, 7));
        list.add(new Beach("9", 126.5f, 7));
        list.add(new Beach("10", 46.2f, 9));

        list.sort(Beach::compareTo);

        for (Beach b : list)
            System.out.println(b.getName());


    }

    @Override
    public synchronized int compareTo(Beach o)
    {
        int i = this.getQuality() - o.getQuality();
        int j = (int) (this.getDistance() - o.getDistance());

        return 100 * j + i;
    }
}
