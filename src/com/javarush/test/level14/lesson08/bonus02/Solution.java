package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        if (a > b)
        {
            for (int i = a; i > 0; i--)
            {
                if (a % i == 0 && b % i == 0)
                {
                    System.out.println(i);
                    break;
                }
            }
        } else
        {
            for (int i = b; i > 0; i--)
            {
                if (a % i == 0 && b % i == 0)
                {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
