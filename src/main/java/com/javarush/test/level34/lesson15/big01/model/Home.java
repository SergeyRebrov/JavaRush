package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Sergey on 08.03.2017.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        width = 2;
        height = 2;
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        graphics.drawOval(x - width/2, y - height/2, width, height);

    }
}
