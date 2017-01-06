package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Random;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution
{
    public static Integer[] sort(Integer[] array)
    {
        //implement logic here

        Arrays.sort(array);

        final int MEDIAN;

        if (array.length % 2 == 0)
        {
            MEDIAN = (array[array.length / 2 - 1] +
                    array[array.length / 2]) / 2;
        } else
        {
            MEDIAN = array[array.length / 2];
        }

        Arrays.sort(array, (o1, o2) ->
        {
            int modul1 = Math.abs(MEDIAN - o1);
            int modul2 = Math.abs(MEDIAN - o2);

            return modul1 - modul2;
        });

        return array;
    }

    public static void main(String[] args)
    {
        Random random = new Random();

        Integer[] array = new Integer[11];

        for (int i = 0; i < 11; i++)
        {
            array[i] = random.nextInt(11);
        }

        for (int i : sort(array))
            System.out.print(i + " ");

        System.out.println();
        System.out.println(array.length);
    }
}
