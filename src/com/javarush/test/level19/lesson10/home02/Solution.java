package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        TreeMap<String, Double> list = new TreeMap<String, Double>();

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String s = null;
        while ((s = reader.readLine()) != null)
        {
            String[] strings = s.split(" ");
            double d = Double.parseDouble(strings[1]);

            if (list.containsKey(strings[0]))
            {
                for (Map.Entry<String, Double> entry : list.entrySet())
                {
                    if (entry.getKey().equals(strings[0]))
                        list.put(strings[0], entry.getValue() + d);
                }
            }
            else
                list.put(strings[0], d);
        }
        reader.close();

        double max = Double.MIN_VALUE;
        for (Map.Entry<String, Double> entry : list.entrySet())
        {
            if (entry.getValue() > max)
                max = entry.getValue();
        }
        for (Map.Entry<String, Double> entry : list.entrySet())
        {
            if (entry.getValue() == max)
                System.out.println(entry.getKey());
        }
    }
}
