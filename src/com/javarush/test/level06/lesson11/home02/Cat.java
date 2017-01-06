package com.javarush.test.level06.lesson11.home02;

/* Статические коты
1. В классе Cat добавь public статическую переменную cats (ArrayList<Cat>).
2. Пусть при каждом создании кота (нового объекта Cat) в переменную cats добавляется этот новый кот. Создать 10 объектов Cat.
3. Метод printCats должен выводить всех котов на экран. Нужно использовать переменную cats.
*/

import java.util.ArrayList;

public class Cat
{
    public Cat()
    {
    }

    public static ArrayList<Cat> cats = new ArrayList<Cat>();

    public static void main(String[] args)
    {

        Cat[] cat = new Cat[10];

        for (int i = 0; i < 10; i++)
        {
            cat[i] = new Cat();
            Cat.cats.add(cat[i]);
        }

        printCats();
    }

    public static void printCats()
    {

        for (Cat catCat : Cat.cats)
        {
            System.out.println(catCat);
        }

    }
}
