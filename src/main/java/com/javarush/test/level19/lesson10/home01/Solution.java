package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
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

        for (Map.Entry<String, Double> entry : list.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }
}
