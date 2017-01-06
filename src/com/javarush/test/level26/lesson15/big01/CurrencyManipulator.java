package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sergey on 02.01.2017.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder());

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() {

        int sum = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public boolean hasMoney() {
        return denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        int sum = expectedAmount;
        Map<Integer, Integer> buffer = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> tmp = new TreeMap<>(Collections.reverseOrder());
        tmp.putAll(denominations);

        for (Map.Entry<Integer, Integer> entry : tmp.entrySet())
        {
            while (entry.getKey() <= sum && entry.getKey() * entry.getValue() > 0)
            {
                if (buffer.containsKey(entry.getKey()))
                    buffer.put(entry.getKey(), buffer.get(entry.getKey()) + 1);
                else
                    buffer.put(entry.getKey(), 1);
                tmp.put(entry.getKey(), tmp.get(entry.getKey()) - 1);
                sum -= entry.getKey();
                if (sum == 0)
                    break;
            }
            if (sum == 0)
                break;
        }

        if (sum > 0)
            throw new NotEnoughMoneyException();
        else
        {
            denominations.putAll(tmp);
            return buffer;
        }
    }
}
