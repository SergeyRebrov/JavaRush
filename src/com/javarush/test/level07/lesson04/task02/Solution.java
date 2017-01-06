package com.javarush.test.level07.lesson04.task02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] list = new String[10];

        for (int i = 0; i < 8; i++)
        {
            list[i] = reader.readLine();
        }

        for (int i = 0; i < list.length; i++)
        {
            int a = list.length - i - 1;
            System.out.println(list[a]);
        }

    }
}