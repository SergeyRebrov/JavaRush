package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String name;
        int age;
        int weight;
        int growth;
        boolean sex;
        String color;

        Human(String name)
        {
            this.name = name;
        }

        Human(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        Human(String name, int age, int weight)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        Human(String name, int age, int weight, int growth)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.growth = growth;
        }

        Human(String name, int age, int weight, int growth, boolean sex)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.growth = growth;
            this.sex = sex;
        }

        Human(String name, int age, int weight, int growth, boolean sex, String color)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.growth = growth;
            this.sex = sex;
            this.color = color;
        }

        Human(String name, boolean sex)
        {
            this.name = name;
            this.sex = sex;
        }

        Human(String name, int age, boolean sex)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        Human(String name, int age, boolean sex, String color)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.color = color;
        }

        Human(String name, int age, boolean sex, String color, int weight)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.color = color;
            this.weight = weight;
        }


    }
}
