package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution
{
    public static int[] getNumbers(int N)
    {
        int[] result = null;
        ArrayList<Integer> numberList = new ArrayList<>();
        Set<Integer> summ = new HashSet<>();
        int number = 1;
        again : while (number <= N)
        {
            char[] numberChars = Integer.toString(number).toCharArray();
            int tmp = 0;


            int d = number;
            int e = 0;
            while (d > 0){
                e += d % 10;
                d /= 10;
            }
            while (summ.iterator().hasNext())
            {
                if (summ.iterator().next() == e)
                {
                    System.out.println("!" + e);
                    continue again;
                }
            }




            for (char c : numberChars)
                tmp += (int) Math.pow(Integer.parseInt(String.valueOf(c)), numberChars.length);
            if (number == tmp)
            {
                numberList.add(number);
                System.out.println(number);
            }
            number++;
        }
        result = new int[numberList.size()];
        for (int j = 0; j < result.length; j++)
            result[j] = numberList.get(j);
        return result;
    }

    public static void main(String[] args)
    {
        getNumbers(40000000);
    }

}
