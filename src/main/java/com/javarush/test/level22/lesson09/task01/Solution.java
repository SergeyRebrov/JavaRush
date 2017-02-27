package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution
{
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args)
    {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileReader fileReader = new FileReader(reader.readLine()))
        {
            StringBuilder words = new StringBuilder();
            while (fileReader.ready())
            {
                words.append((char) fileReader.read());
            }

            String[] list = words.toString().split("\\s|\\n");

            for (int i = list.length - 1; i >= 0; i--)
            {
                StringBuilder tmp = new StringBuilder(list[i]).reverse();
                String word = "";
                for (int j = 0; j < i; j++)
                {
                    if (list[j].equals(tmp.toString()))
                    {
                        word = list[j];
                    }
                }

                if (!word.equals(""))
                {
                    Pair pair = new Pair();
                    pair.first = word;
                    pair.second = list[i];
                    result.add(pair);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static class Pair
    {
        String first;
        String second;

        @Override
        public String toString()
        {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
