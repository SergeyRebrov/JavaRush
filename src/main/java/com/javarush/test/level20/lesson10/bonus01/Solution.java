package com.javarush.test.level20.lesson10.bonus01;

import java.util.*;

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
public class Solution {
    public static int[] getNumbers(int N) {
        int[] result = null;
        List<Integer> integerList = new ArrayList<>();

        int porog = 10;
        int[] massiv = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int number = 1; number < N; number++) {

            if (number == porog) {
                porog *= 10;
                for (int i = 0; i < massiv.length; i++)
                    massiv[i] *= i;
            }

            int sum = 0;
            int tmp = number;

            while (tmp > 0) {
                int x = tmp % 10;
                sum += massiv[x];
                if (sum > number)
                    break;

                tmp /= 10;
            }

            if (sum == number) {
                integerList.add(number);
            }

        }

        result = new int[integerList.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = integerList.get(i);

        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Integer.MAX_VALUE)));
        long end = System.currentTimeMillis();
        System.out.println(end - start + " millisecond");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");

    }
}
