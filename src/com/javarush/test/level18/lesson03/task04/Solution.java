package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (fileInputStream.available() > 0)
        {
            list.add(fileInputStream.read());
        }
        int count = Integer.MAX_VALUE;
        String s = "";
        for (int i = list.size() - 1; i >= 0; i--)
        {
            int sum = 1;
            for (int j = 0; j < i; j++)
            {
                if (list.get(j) == list.get(i))
                    sum++;
            }
            if (count > sum)
            {
                count = sum;
                s = list.get(i) + " ";
            }
            else if (count == sum)
            {
                if (s.contains(Integer.toString(list.get(i))))
                {}
                else
                    s += list.get(i) + " ";
            }
        }
        System.out.println(s);
        fileInputStream.close();
    }
}
