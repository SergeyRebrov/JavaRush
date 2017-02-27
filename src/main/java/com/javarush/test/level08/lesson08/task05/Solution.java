package com.javarush.test.level08.lesson08.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Ивашов", "Иван");
        map.put("Петров", "Петр");
        map.put("Сидоров", "Сидр");
        map.put("Леонов", "Леонид");
        map.put("Николаев", "Валентин");
        map.put("Степанов", "Степан");
        map.put("Иванов", "Петр");
        map.put("Павлов", "Леонид");
        map.put("Москвин", "Николай");
        map.put("Бочаров", "Иван");
        return (HashMap<String, String>) map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {

        int a = map.size();
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> pair : map.entrySet())
        {
            list.add(pair.getValue());
        }

        ArrayList<String> copy = new ArrayList<String>();
        for (int i = list.size() - 1; i >= 0; i--)
        {
            String s = list.get(i);
            for (int j = 0; j < i; j++)
            {
                if (s.equals(list.get(j)))
                    copy.add(s);
            }
        }

        for (String s : copy)
        {
            removeItemFromMapByValue((HashMap<String, String>) map, s);
        }


    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args)
    {
        Map<String, String> map = createMap();
        removeTheFirstNameDuplicates((HashMap<String, String>) map);
        for (Map.Entry<String, String> pair : map.entrySet())
        {
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.println(key + ":" + value);
        }
    }
}
