package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Sergey on 08.03.2017.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int x = getX();
        int y = getY();

        if (direction == Direction.DOWN) y += Model.FIELD_SELL_SIZE;

        if (direction == Direction.UP) y -= Model.FIELD_SELL_SIZE;

        if (direction == Direction.LEFT) x -= Model.FIELD_SELL_SIZE;

        if (direction == Direction.RIGHT) x += Model.FIELD_SELL_SIZE;

        return x == gameObject.getX() && y == gameObject.getY();
    }
}
