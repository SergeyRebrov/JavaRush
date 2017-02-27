package com.javarush.test.level07.lesson04.task05;

/* Один большой массив и два маленьких
1. Создать массив на 20 чисел.
2. Ввести в него значения с клавиатуры.
3. Создать два массива на 10 чисел каждый.
4. Скопировать большой массив в два маленьких: половину чисел в первый маленький, вторую половину во второй маленький.
5. Вывести второй маленький массив на экран, каждое значение выводить с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int[] list = new int[20];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < list.length; i++)
        {
            int a = Integer.parseInt(reader.readLine());
            list[i] = a;
        }

        int[] nList = new int[10];
        int[] mList = new int[10];

        System.arraycopy(list, 0, nList, 0, nList.length);
        System.arraycopy(list, list.length - nList.length, mList, 0, mList.length);

        for (int i = 0; i < mList.length; i++)
        {
            System.out.println(mList[i]);
        }

    }
}
