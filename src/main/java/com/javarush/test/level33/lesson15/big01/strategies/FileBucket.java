package com.javarush.test.level33.lesson15.big01.strategies;


import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Sergey on 03.03.2017.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path)))
        {
            out.writeObject(entry);
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry()
    {
        if (getFileSize() == 0)
            return null;

        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path)))
        {
            return (Entry) in.readObject();
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
}
