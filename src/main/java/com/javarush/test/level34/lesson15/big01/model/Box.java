package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Sergey on 08.03.2017.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(x - width / 2, y - height / 2, width, height);
        graphics.drawLine(x - width / 2, y - height / 2, x - width / 2 + width, y - height / 2 + height);
        graphics.drawLine(x - width / 2 + width, y - height / 2, x - width / 2, y - height / 2 + height);
    }

    @Override
    public void move(int x, int y)
    {
        this.x += x;
        this.y += y;
    }
}
