package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution
{
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass
    {
    }

    public static Solution[] getTwoSolutions()
    {
        Solution solution = new Solution();
        solution.innerClasses[0] = solution.new InnerClass();
        solution.innerClasses[1] = solution.new InnerClass();

        Solution solution1 = new Solution();
        solution1.innerClasses[0] = solution1.new InnerClass();
        solution1.innerClasses[1] = solution1.new InnerClass();

        Solution[] list = new Solution[2];
        list[0] = solution;
        list[1] = solution1;
        return list;
    }
}
