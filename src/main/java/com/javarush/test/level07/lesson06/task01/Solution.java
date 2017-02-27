package com.javarush.test.level07.lesson06.task01;

import java.util.ArrayList;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/
public class Solution
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Помидор");
        list.add("Арбуз");
        list.add("Картофель");
        list.add("Перец");
        list.add("Баклажан");

        int s = list.size();

        System.out.println(s);

        for (int i = 0; i < s; i++)
        {
            System.out.println(list.get(i));
        }

    }
}
