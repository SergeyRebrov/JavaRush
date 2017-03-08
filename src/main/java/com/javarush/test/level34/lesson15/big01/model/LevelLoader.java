package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 08.03.2017.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        Player player = new Player(20 + Model.FIELD_SELL_SIZE/2, 40 + Model.FIELD_SELL_SIZE/2);
        Home home = new Home(200 + Model.FIELD_SELL_SIZE/2,200 + Model.FIELD_SELL_SIZE/2);
        Box box = new Box(60 + Model.FIELD_SELL_SIZE/2, 40 + Model.FIELD_SELL_SIZE/2);
        Set<Wall> walls = new HashSet<>();
        for (int i = 0; i < 5; i++)
        {
            walls.add(new Wall(40 + Model.FIELD_SELL_SIZE/2, 40 * i + Model.FIELD_SELL_SIZE/2));
        }

        return new GameObjects(walls, new HashSet<Box>(Collections.singletonList(box)), new HashSet<Home>(Collections.singletonList(home)), player);
    }
}
