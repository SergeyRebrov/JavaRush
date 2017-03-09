package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
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
        Player player = null;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        int maxLevel = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(levels.toString())));

            while (reader.ready())
            {
                String string = reader.readLine();
                if (string.contains("Maze:"))
                {
                    string = string.replaceAll("[^0-9]", "");
                    if (maxLevel < Integer.parseInt(string))
                        maxLevel = Integer.parseInt(string);
                }
            }
            if (level > maxLevel)
                level = level % maxLevel;

            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(levels.toString())));

            while (reader.ready())
            {
                String string = reader.readLine();
                if (string.contains("Maze: " + level))
                    break;
            }
            reader.readLine();

            String[] sizeX = reader.readLine().split(" ");
            String[] sizeY = reader.readLine().split(" ");

            int width = Integer.parseInt(sizeX[2]);
            int height = Integer.parseInt(sizeY[2]);

            reader.readLine();
            reader.readLine();
            reader.readLine();

            for (int y = 0; y < height; y++)
            {
                char[] chars = reader.readLine().toCharArray();
                for (int x = 0; x < width; x++)
                {
                    switch (chars[x])
                    {
                        case 'X':
                            walls.add(new Wall(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '*':
                            boxes.add(new Box(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '.':
                            homes.add(new Home(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '@':
                            player = new Player(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2);
                            break;
                        case '&':
                            boxes.add(new Box(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            homes.add(new Home(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        default:
                            break;
                    }
                }
            }


            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
