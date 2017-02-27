package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInput = new FileInputStream(reader.readLine());
        ArrayList<Integer> list = new ArrayList<Integer>();

        while (fileInput.available() > 0)
        {
            list.add(fileInput.read());
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i : list)
        {
            if (!temp.contains(i))
                temp.add(i);
        }

        Collections.sort(temp);
        for (int i : temp)
        {
            System.out.print(i + " ");
        }
        fileInput.close();
    }
}
