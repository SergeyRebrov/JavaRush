package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution
{
    public Solution()
    {
    }

    public Solution(String s)
    {
    }

    public Solution(int i)
    {
    }

    private Solution(String s, int i)
    {
    }

    private Solution(int i, String s)
    {
    }

    private Solution(int i, String s, Solution n)
    {
    }

    protected Solution(Solution n, String s, int i)
    {
    }

    protected Solution(Solution n, int i)
    {
    }

    protected Solution(Solution n, String s)
    {
    }

    Solution(String s, Solution n)
    {
    }

    Solution(int i, Solution n)
    {
    }

    Solution(int i, Solution n, String s)
    {
    }
}

