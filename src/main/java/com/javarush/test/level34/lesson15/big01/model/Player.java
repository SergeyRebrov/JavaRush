package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Sergey on 08.03.2017.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(x - width / 2, y - height / 2, width, height);

    }

    @Override
    public void move(int x, int y)
    {
        this.x += x;
        this.y += y;
    }
}
