package com.javarush.test.level12.lesson04.task02;

/* print(int) и print(Integer)
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Solution sol = new Solution();
        sol.print(5);
        Integer a = 25;
        sol.print(a);

    }

    public void print(int i)
    {
        System.out.println(i);
    }

    public void print(Integer a)
    {
        System.out.println(a);
    }
}
