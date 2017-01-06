package com.javarush.test.level08.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Нужно добавить в программу новую функциональность
Задача: Программа определяет, какая семья (фамилию) живёт в доме с указанным номером.
Новая задача: Программа должна работать не с номерами домов, а с городами:
Пример ввода:
Москва
Ивановы
Киев
Петровы
Лондон
Абрамовичи

Лондон

Пример вывода:
Абрамовичи
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> city = new ArrayList<String>();
        List<String> family = new ArrayList<String>();

        while (true)
        {
            String ct = reader.readLine();
            if (ct.isEmpty()) break;

            String fml = reader.readLine();
            city.add(ct);
            family.add(fml);
        }

        //read home number
        String nCity = reader.readLine();

        for (int i = 0; i < city.size(); i++)
        {
            if (nCity.equals(city.get(i)))
            {
                System.out.println(family.get(i));
            }
        }
    }
}
