package com.javarush.test.level36.lesson08.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Set<Character> symbols = new TreeSet<>(new Comparator<Character>()
        {
            @Override
            public int compare(Character o1, Character o2)
            {
                return o1.toString().compareToIgnoreCase(o2.toString());
            }
        });
        while (reader.ready())
        {
            char c = (char) reader.read();
            if (String.valueOf(c).matches("[a-zA-Z]"))
                symbols.add(c);
        }

        int count = 0;
        for (char c : symbols)
        {
            if (count == 5)
                break;
            System.out.print(c);
            count++;
        }

        reader.close();
    }
}
