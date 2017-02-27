package com.javarush.test.level05.lesson05.task03;

/* Геттеры и сеттеры для класса Dog
Создать class Dog. У собаки должна быть кличка String name и возраст int age.
Создайте геттеры и сеттеры для всех переменных класса Dog.
*/

public class Dog
{
    private String name;
    private int age;

    public void setName(String nameDog)
    {
        name = nameDog;
    }

    public String getName()
    {
        return name;
    }

    public void setAge(int ageDog)
    {
        age = ageDog;
    }

    public int getAge()
    {
        return age;
    }
}
