package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Sergey on 08.03.2017.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(150, 75, 0));
        graphics.fillRect(x - width/2, y - height/2, width, height);

    }
}
