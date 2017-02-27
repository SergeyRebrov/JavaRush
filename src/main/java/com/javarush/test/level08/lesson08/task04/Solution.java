package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1970"));
        map.put("Rickman", new Date("JULY 15 1980"));
        map.put("Sandler", new Date("SEPTEMBER 2 1990"));
        map.put("Garcia", new Date("JANUARY 8 1985"));
        map.put("Benson", new Date("APRIL 16 1983"));
        map.put("Schwarzenegger", new Date("OCTOBER 26 2000"));
        map.put("Hopkins", new Date("AUGUST 30 1946"));
        map.put("Brody", new Date("MARCH 18 1964"));
        map.put("Adams", new Date("MAY 5 1959"));
        map.put("Banderas", new Date("JULY 31 1991"));

        return (HashMap<String, Date>) map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, Date> pair = iterator.next();
            Date date = pair.getValue();
            int month = date.getMonth();
            if (month == 5 || month == 6 || month == 7)
                iterator.remove();
        }
    }

    public static void main(String[] args)
    {
        Map<String, Date> map = createMap();
        removeAllSummerPeople((HashMap<String, Date>) map);
        for (Map.Entry<String, Date> pair : map.entrySet())
        {
            String key = pair.getKey();
            Date value = pair.getValue();
            System.out.println(key + ":" + value);
        }
    }
}
