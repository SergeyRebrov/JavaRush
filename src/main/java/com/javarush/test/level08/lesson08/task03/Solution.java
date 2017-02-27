package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Иванов", "Иван");
        map.put("Петров", "Петр");
        map.put("Сидоров", "Сидр");
        map.put("Леонов", "Леонид");
        map.put("Николаев", "Николай");
        map.put("Степанов", "Степан");
        map.put("Ивашов", "Петр");
        map.put("Павлов", "Леонид");
        map.put("Москвин", "Николай");
        map.put("Бочаров", "Иван");
        return (HashMap<String, String>) map;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int a = 0;
        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String s = pair.getValue();
            if (name.equals(s))
                a++;
        }
        return a;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int a = 0;
        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String s = pair.getKey();
            if (lastName.equals(s))
                a++;
        }
        return a;
    }

    public static void main(String[] args)
    {
        Map<String, String> map = createMap();
        getCountTheSameFirstName((HashMap<String, String>) map, "Иван");
        getCountTheSameLastName((HashMap<String, String>) map, "Николаев");
    }
}
