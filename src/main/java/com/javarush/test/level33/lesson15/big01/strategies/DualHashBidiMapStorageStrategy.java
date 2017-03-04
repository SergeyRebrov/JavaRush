package com.javarush.test.level33.lesson15.big01.strategies;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * Created by Sergey on 04.03.2017.
 */
public class DualHashBidiMapStorageStrategy implements StorageStrategy
{
    private DualHashBidiMap data = new DualHashBidiMap();

    @Override
    public boolean containsKey(Long key)
    {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value)
    {
        return (Long) data.getKey(value);
    }

    @Override
    public String getValue(Long key)
    {
        return (String) data.get(key);
    }
}
