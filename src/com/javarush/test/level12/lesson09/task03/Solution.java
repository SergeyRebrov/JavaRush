package com.javarush.test.level12.lesson09.task03;

/* Fly, Move, Eat для классов Dog, Car, Duck, Airplane
Есть public интерфейсы Fly(летать), Move(передвигаться), Eat(есть).
Добавь эти интерфейсы классам Dog(собака), Car(автомобиль), Duck(утка), Airplane(самолет).
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public interface Fly
    {
        void fly();
    }

    public interface Move
    {
        void move();
    }

    public interface Eat
    {
        void eat();
    }

    public class Dog implements Move, Eat
    {
        public void move()
        {
        }

        public void eat()
        {
        }
    }

    public class Duck implements Fly, Move, Eat
    {
        public void fly()
        {
        }

        public void move()
        {
        }

        public void eat()
        {
        }
    }

    public class Car implements Move
    {
        public void move()
        {
        }
    }

    public class Airplane implements Fly, Move
    {
        public void fly()
        {
        }

        public void move()
        {
        }
    }
}
